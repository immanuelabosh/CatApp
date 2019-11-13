package com.example.catapp.Fragments;

import android.os.Bundle;
import android.util.Log;
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
import com.example.catapp.MainActivity;
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
        clearSearch.setVisible(FALSE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        setHasOptionsMenu(true);


        recyclerView = view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        catAdapter = new CatAdapter(getContext());

        updateRecyclerView("https://api.thecatapi.com/v1/breeds?api-key="+getString(R.string.key));

        return view;
    }

    // This is just an example of a way that the Fragment can communicate with the parent Activity.
    // Specifically, this is using a method belonging to the parent.
    @Override
    public void onResume() {
        super.onResume();
        MainActivity parent = (MainActivity) getActivity();
        //parent.showCoolMessage("cool (from ArticleRecyclerFragment onResume)");
    }

    // Call this method to launch the edit dialog
    private void showEditDialog() {
        FragmentManager fm = getFragmentManager();
        EditTextFragment editTextFragment = EditTextFragment.newInstance("Search Breeds");
        // SETS the target fragment for use later when sending results
        editTextFragment.setTargetFragment(SearchFragment.this, 300);
        editTextFragment.show(fm, "fragment_edit_text");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == clearSearch && clearSearch.isVisible()){
            clearSearch.setVisible(FALSE);
            updateRecyclerView("https://api.thecatapi.com/v1/breeds");
        }else {
            showEditDialog();
        }
        return true;
    }

    public void updateRecyclerView(String url){
        final RequestQueue requestQueue =  Volley.newRequestQueue(getActivity());

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

    }


    // This is called when the dialog is completed and the results have been passed
    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this.getContext(),"Searching for breed " + inputText, Toast.LENGTH_SHORT).show();
        if (inputText.length() == 0) {
            clearSearch.setVisible(FALSE);
            updateRecyclerView("https://api.thecatapi.com/v1/breeds");
        }else {
            updateRecyclerView("https://api.thecatapi.com/v1/breeds" + "/search?q=" + inputText);
            clearSearch.setVisible(TRUE);
        }
    }
}
