package com.example.catapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.catapp.Database.Cat;
import com.example.catapp.R;

//this handles the webview for wikipedia
public class WebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //change the title
        setTitle("Wikipedia");

        //init the webview
        WebView mWebView = findViewById(R.id.webview);

        //getting the intent
        Intent intent = getIntent();

        //getting the cat from the intent
        Cat cat = (Cat) intent.getSerializableExtra("cat");

        //this is stuff i found on stackoverflow, it does things
        //https://stackoverflow.com/questions/50990374/webview-doesnt-display-anything
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        webSettings.setBlockNetworkLoads(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mWebView.setWebChromeClient(new WebChromeClient());

        //load the wikipedia link
        mWebView.loadUrl(cat.getWikipediaUrl());


    }
}
