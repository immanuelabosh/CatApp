package com.example.catapp;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class CatDetailActivity extends AppCompatActivity {
    ConstraintLayout bookConstraintLayout;
    TextView nameTextView;
    TextView informationTextView;
    ImageView catImageView;
    String information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);


        informationTextView = findViewById(R.id.information);
        catImageView = findViewById(R.id.imageView);
        nameTextView = findViewById(R.id.name);


        Intent intent = getIntent();

        Cat cat = (Cat) intent.getSerializableExtra("cat");
        nameTextView.setText(cat.getName());


        information = "\nAlt Names: ";

        if (cat.getAltNames().isEmpty()){
            information += " none";
        }else {
            information += cat.getAltNames();
        }
        information += "\n\nDescription: "  + cat.getDescription();
        information += "\n\nTemperament: " + cat.getTemperament();
        information += "\n\nOrigin: " + cat.getOrigin();
        information += "\n\nLifespan: " + cat.getLifeSpan();
        information += "\n\nWikipedia Link: " + cat.getWikipediaUrl();
        information += "\n\nDog Friendliness (1-5): " + cat.getDogFriendly();

        setImageAndWeight(cat.getId());

    }
    public void setImageAndWeight(String breedID) {
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breedID;
        final RequestQueue requestQueue =  Volley.newRequestQueue(getApplicationContext());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                CatImage[] objectsArray = gson.fromJson(response, CatImage[].class);
                Glide.with(getApplicationContext()).load(objectsArray[0].getUrl()).into(catImageView);
                information += "\n\nWeight: " + objectsArray[0].getWeight() + " kg";
                informationTextView.setText(information);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

    }


}
