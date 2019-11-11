package com.example.catapp.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Cat implements Serializable
{
    @SerializedName("alt_names")
    @Expose
    public String altNames;
    @SerializedName("experimental")
    @Expose
    public int experimental;
    @SerializedName("hairless")
    @Expose
    public int hairless;
    @SerializedName("hypoallergenic")
    @Expose
    public int hypoallergenic;
    @PrimaryKey
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
    public int natural;
    @SerializedName("origin")
    @Expose
    public String origin;
    @SerializedName("rare")
    @Expose
    public int rare;
    @SerializedName("rex")
    @Expose
    public int rex;
    @SerializedName("short_legs")
    @Expose
    public int shortLegs;
    @SerializedName("suppressed_tail")
    @Expose
    public int suppressedTail;
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
