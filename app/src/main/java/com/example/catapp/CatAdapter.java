package com.example.catapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catapp.Activities.CatDetailActivity;
import com.example.catapp.Database.AppDatabase;
import com.example.catapp.Database.Cat;

import java.util.List;

//This is my recycler view adapter, i use it for my search and favourites fragment

// We need to give a type in angle brackets <> when we extend RecyclerView.Adapter
// It's saying that we want an adapter that adapts to CatViewHolder (our custom ViewHolder)
public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    // class variable that holds the data that we want to adapt
    private List<Cat> catsToAdapt;

    //get database instance
    AppDatabase db;

    public CatAdapter(Context context) {
        db = AppDatabase.getInstance(context);
    }

    public void setData(List<Cat> catsToAdapt) {
        // This is basically a Setter that we use to give data to the adapter
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // First create a View from the layout file. It'll probably be a ViewGroup like
        // ConstraintLayout that contains more Views inside it.
        // This view now represents your entire one item.
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat, parent, false);

        // Then create an instance of your custom ViewHolder with the View you got from inflating
        // the layout.
        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CatViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);

        //check if cat is in favourites database
        if ((db.catDao().getCatExists(catAtPosition.getId())) == 1){
            //if it is change the star to a filled in one to show that to the user
            holder.isBookmarked = true;
            holder.favouriteImageView.setImageResource(R.drawable.ic_star_black_24dp);
        //if it isnt, set it to a border star
        }else {
            holder.isBookmarked = false;
            holder.favouriteImageView.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        //show the cat breeds name
        holder.breedTextView.setText(catAtPosition.getName());

        //when they click on a cat item, open the detail activity
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, CatDetailActivity.class);
                intent.putExtra("cat", catAtPosition);
                context.startActivity(intent);
            }
        });

        //toggle functionality for the the favourites feature
        // We can define onClickListener for bookmark button here because it depends on data
        // unique to this ViewHolder (i.e. whether this item has already been bookmarked or not)
        holder.favouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its bookmarked
                if(holder.isBookmarked) {
                    //delete it and change the image
                    db.catDao().delete(catAtPosition);
                    holder.favouriteImageView.setImageResource(R.drawable.ic_star_border_black_24dp);
                //if its not bookmarked, put it in the database and change the image
                } else {
                    db.catDao().insert(catAtPosition);
                    holder.favouriteImageView.setImageResource(R.drawable.ic_star_black_24dp);
                }
                //invert the status
                holder.isBookmarked = !holder.isBookmarked;
            }
        });

        // maybe add a share cat function

    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    // ViewHolder represents one item, but doesn't have data when it's first constructed.
    // We assign the data in onBindViewHolder.
    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView breedTextView;
        public ImageView favouriteImageView;
        public boolean isBookmarked = false;

        // This constructor is used in onCreateViewHolder
        public CatViewHolder(View v) {
            super(v);  // runs the constructor for the ViewHolder superclass
            view = v;
            breedTextView = v.findViewById(R.id.textView);
            favouriteImageView = v.findViewById(R.id.imageView2);

        }
    }


}
