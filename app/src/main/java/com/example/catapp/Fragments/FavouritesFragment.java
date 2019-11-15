package com.example.catapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catapp.Database.AppDatabase;
import com.example.catapp.CatAdapter;
import com.example.catapp.Database.Cat;
import com.example.catapp.Activities.MainActivity;
import com.example.catapp.R;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

//this is my favourites fragment
//it displays favourites from the database
public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CatAdapter catAdapter = new CatAdapter(getContext());
    //get the database
    AppDatabase db = AppDatabase.getInstance(getContext());
    private MenuItem clearFav;


    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_favourites, menu);
        clearFav = menu.findItem(R.id.clearFav);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        setHasOptionsMenu(true);

        //setup the recyclerview
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        //i updated the recyclerview on resume because its kind of unneccessary to do it twice on load

        return view;
    }

    //update the recycler view on resume
    @Override
    public void onResume() {
        updateRecyclerView();
        super.onResume();
    }

    //when the delete all favourites button is clicked in the action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //delete everything in the database
        db.catDao().delete(db.catDao().getAllCats());
        //update the recyclerview
        updateRecyclerView();
        return true;
    }

    //this is a function that fills the recycler view with data and sets it
    public void updateRecyclerView(){
        List<Cat> cats = db.catDao().getAllCats();
        catAdapter.setData(cats);
        recyclerView.setAdapter(catAdapter);
        //this just makes the clear all favourites option invisible if there are no favourites to clear
        if (cats.isEmpty()){
            clearFav.setVisible(FALSE);
        }else {
            clearFav.setVisible(TRUE);
        }
    }


}
