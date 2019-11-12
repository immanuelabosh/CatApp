package com.example.catapp.Database;

import androidx.annotation.NonNull;
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
    private String altNames;
    @SerializedName("experimental")
    @Expose
    private int experimental;
    @SerializedName("hairless")
    @Expose
    private int hairless;
    @SerializedName("hypoallergenic")
    @Expose
    private int hypoallergenic;
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id = "";
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("natural")
    @Expose
    private int natural;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("rare")
    @Expose
    private int rare;
    @SerializedName("rex")
    @Expose
    private int rex;
    @SerializedName("short_legs")
    @Expose
    private int shortLegs;
    @SerializedName("suppressed_tail")
    @Expose
    private int suppressedTail;
    @SerializedName("temperament")
    @Expose
    private String temperament;
    @SerializedName("weight_imperial")
    @Expose
    private String weightImperial;
    @SerializedName("wikipedia_url")
    @Expose
    private String wikipediaUrl;

    public String getAltNames() {
        return altNames;
    }

    public void setAltNames(String altNames) {
        this.altNames = altNames;
    }

    public int getExperimental() {
        return experimental;
    }

    public void setExperimental(int experimental) {
        this.experimental = experimental;
    }

    public int getHairless() {
        return hairless;
    }

    public void setHairless(int hairless) {
        this.hairless = hairless;
    }

    public int getHypoallergenic() {
        return hypoallergenic;
    }

    public void setHypoallergenic(int hypoallergenic) {
        this.hypoallergenic = hypoallergenic;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNatural() {
        return natural;
    }

    public void setNatural(int natural) {
        this.natural = natural;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getRare() {
        return rare;
    }

    public void setRare(int rare) {
        this.rare = rare;
    }

    public int getRex() {
        return rex;
    }

    public void setRex(int rex) {
        this.rex = rex;
    }

    public int getShortLegs() {
        return shortLegs;
    }

    public void setShortLegs(int shortLegs) {
        this.shortLegs = shortLegs;
    }

    public int getSuppressedTail() {
        return suppressedTail;
    }

    public void setSuppressedTail(int suppressedTail) {
        this.suppressedTail = suppressedTail;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getWeightImperial() {
        return weightImperial;
    }

    public void setWeightImperial(String weightImperial) {
        this.weightImperial = weightImperial;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }
}
