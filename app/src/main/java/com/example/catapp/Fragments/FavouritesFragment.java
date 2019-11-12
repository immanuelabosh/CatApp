package com.example.catapp.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.example.catapp.MainActivity;
import com.example.catapp.R;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CatAdapter catAdapter = new CatAdapter(getContext());
    AppDatabase db = AppDatabase.getInstance(getContext());

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_favourites, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        setHasOptionsMenu(true);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String username = sharedPreferences.getString(getString(R.string.username), "defaultValue");

        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        updateRecyclerView();

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppDatabase db = AppDatabase.getInstance(getContext());
        db.catDao().delete(db.catDao().getAllCats());
        updateRecyclerView();
        return true;
    }

    public void updateRecyclerView(){
        catAdapter.setData(db.catDao().getAllCats());
        recyclerView.setAdapter(catAdapter);
    }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.
    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        //parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");
    }

}
