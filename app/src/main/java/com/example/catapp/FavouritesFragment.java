package com.example.catapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_username_title_bar, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        final CatAdapter catAdapter = new CatAdapter();
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());
        String url = "https://api.thecatapi.com/v1/breeds?api-key="+getString(R.string.key);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cat[] objectsArray = gson.fromJson(response, Cat[].class);
                List<Cat> objectsList = Arrays.asList(objectsArray);
                catAdapter.setData(objectsList);
                recyclerView.setAdapter(catAdapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
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
