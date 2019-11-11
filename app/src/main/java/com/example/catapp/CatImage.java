package com.example.catapp;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CatImage {

    @SerializedName("height")
    @Expose
    public int height;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("width")
    @Expose
    public int width;

}
