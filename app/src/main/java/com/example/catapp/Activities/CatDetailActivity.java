package com.example.catapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.catapp.Database.AppDatabase;
import com.example.catapp.Database.Cat;
import com.example.catapp.Database.CatImage;
import com.example.catapp.R;
import com.google.gson.Gson;


public class CatDetailActivity extends AppCompatActivity {
    ConstraintLayout bookConstraintLayout;
    TextView nameTextView;
    TextView informationTextView;
    ImageView catImageView;
    String information;
    ImageView link;
    ImageView favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        //init the various views
        informationTextView = findViewById(R.id.information);
        catImageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.name);
        favourite = findViewById(R.id.favourite);
        link = findViewById(R.id.wikipedia);


        //getting the intent
        final Intent intent = getIntent();

        //getting the cat from the intent
        final Cat cat = (Cat) intent.getSerializableExtra("cat");

        //setting the cat name
        nameTextView.setText(cat.getName());
        //setting the title in the actionbar to the cat name
        setTitle(cat.getName());

        //filling in all the info about the cats
        information = "\nAlt Names: ";
        //sometimes cats dont have alt names and im just letting the user know that
        if (cat.getAltNames() == null || cat.getAltNames().isEmpty()){
            information += "None";
        }else {
            information += cat.getAltNames();
        }
        information += "\n\nDescription: "  + cat.getDescription();
        information += "\n\nTemperament: " + cat.getTemperament();
        information += "\n\nOrigin: " + cat.getOrigin();
        information += "\n\nLifespan: " + cat.getLifeSpan() + " years";
        information += "\n\nWikipedia Link: " + cat.getWikipediaUrl();
        information += "\n\nDog Friendliness (1-5): " + cat.getDogFriendly();

        //here i set the image and get the weight of the cat
        setImageAndWeight(cat.getId());

        /*
        here I check whether the cat is favourited, if it is I change the imageview to a filled in
        black star
        */
        final AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        final boolean[] isBookmarked = {db.catDao().getCatExists(cat.getId()) == 1};
        if (isBookmarked[0]) favourite.setImageResource(R.drawable.ic_star_black_24dp);

        //when the favourite button is clicked
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check if the cat is favourited
                if(isBookmarked[0]) {
                    //if it is delete it
                    db.catDao().delete(cat);
                    favourite.setImageResource(R.drawable.ic_star_border_black_24dp);
                } else {
                    //if it isnt, favourite it
                    db.catDao().insert(cat);
                    favourite.setImageResource(R.drawable.ic_star_black_24dp);
                }
                //invert the isBookmarked state
                isBookmarked[0] = !isBookmarked[0];
            }
        });

        //when the link button is clicked
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open the webview activity
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("cat", cat);
                startActivity(intent);
            }
        });


    }

    //this sets the imageview and gets the weight
    public void setImageAndWeight(String breedID) {
        //append the breed im looking for images of
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breedID;
        //standard volley request json stuff here
        final RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatImage[] objectsArray = gson.fromJson(response, CatImage[].class);
                //set the image with glide
                Glide.with(getApplicationContext()).load(objectsArray[0].getUrl()).into(catImageView);
                //add the weight info
                information += "\n\nWeight: " + objectsArray[0].getWeight() + " kg";
                //set the text view
                informationTextView.setText(information);
                requestQueue.stop();
            }
        };
        //if volley doesnt work
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //if theres an error, let the user know there was one
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                informationTextView.setText("Sorry, there was an error retrieving the info you wanted :(");
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        //make the request
        requestQueue.add(stringRequest);

    }


}
