package com.example.catapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.catapp.Database.Cat;
import com.example.catapp.CatAdapter;
import com.example.catapp.R;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class SearchFragment extends Fragment implements EditTextFragment.EditTextFragmentListener {
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private MenuItem clearSearch;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_title_bar, menu);
        clearSearch = menu.findItem(R.id.clearSearch);
        //hide clearsearch if the user hasnt searched for anything
        clearSearch.setVisible(FALSE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        setHasOptionsMenu(true);

        //set up the recyclerview
        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        catAdapter = new CatAdapter(getContext());

        //i updated the recyclerview on resume because its kind of unneccessary to do it twice on load

        return view;
    }

    //on resume, update the recyclerview
    @Override
    public void onResume() {
        updateRecyclerView("https://api.thecatapi.com/v1/breeds");
        super.onResume();
    }

    // Call this method to launch the edit dialog
    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        EditTextFragment editTextFragment = EditTextFragment.newInstance("Search Breeds");
        // SETS the target fragment for use later when sending results
        editTextFragment.setTargetFragment(SearchFragment.this, 300);
        editTextFragment.show(fm, "fragment_edit_text");
    }

    //when an item in the action bar is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //if clearsearch was pressed
        if (item == clearSearch ){
            //make it invisible
            clearSearch.setVisible(FALSE);
            //update the recycler view to show all the breeds
            updateRecyclerView("https://api.thecatapi.com/v1/breeds");
        //otherwise that means the search button was clicked
        }else {
            //show the user a dialog to get their search query
            showEditDialog();
        }
        return true;
    }

    //pass a url and it will update the recyclerview using the results from the url
    public void updateRecyclerView(String url){
        //standard volley stuff
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                Cat[] objectsArray = gson.fromJson(response, Cat[].class);
                //get the data
                List<Cat> objectsList = Arrays.asList(objectsArray);
                //set the adapter and recyclerview
                catAdapter.setData(objectsList);
                recyclerView.setAdapter(catAdapter);
                requestQueue.stop();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tell the user when theres an error
                Toast.makeText(getContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener,
                errorListener);
        requestQueue.add(stringRequest);

    }


    // This is called when the dialog is completed and the results have been passed
    @Override
    public void onFinishEditDialog(String inputText) {
        //tell the user you're looking
        Toast.makeText(this.getContext(),"Searching for breed " + inputText, Toast.LENGTH_SHORT).show();
        //if they didnt type anything
        if (inputText.length() == 0) {
            //clear the results and hide the clear search button
            clearSearch.setVisible(FALSE);
            updateRecyclerView("https://api.thecatapi.com/v1/breeds");
        //if they typed somethign
        }else {
            //update the recycler view and show the clear search button
            updateRecyclerView("https://api.thecatapi.com/v1/breeds" + "/search?q=" + inputText);
            clearSearch.setVisible(TRUE);
        }
    }
}
