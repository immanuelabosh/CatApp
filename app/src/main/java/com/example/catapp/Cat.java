package com.example.catapp;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cat implements Serializable
{
    @SerializedName("alt_names")
    @Expose
    public String altNames;
    @SerializedName("experimental")
    @Expose
    public Integer experimental;
    @SerializedName("hairless")
    @Expose
    public Integer hairless;
    @SerializedName("hypoallergenic")
    @Expose
    public Integer hypoallergenic;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("life_span")
    @Expose
    public String lifeSpan;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("natural")
    @Expose
    public Integer natural;
    @SerializedName("origin")
    @Expose
    public String origin;
    @SerializedName("rare")
    @Expose
    public Integer rare;
    @SerializedName("rex")
    @Expose
    public Integer rex;
    @SerializedName("short_legs")
    @Expose
    public Integer shortLegs;
    @SerializedName("suppressed_tail")
    @Expose
    public Integer suppressedTail;
    @SerializedName("temperament")
    @Expose
    public String temperament;
    @SerializedName("weight_imperial")
    @Expose
    public String weightImperial;
    @SerializedName("wikipedia_url")
    @Expose
    public String wikipediaUrl;

    public String getName() {
        return name;
    }
}
