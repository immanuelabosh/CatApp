package com.example.catapp.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//this is my cat class
//i store this in the database
//I generated this from the json using http://www.jsonschema2pojo.org/
@Entity
public class Cat implements Serializable {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cfa_url")
    @Expose
    private String cfaUrl;
    @SerializedName("vetstreet_url")
    @Expose
    private String vetstreetUrl;
    @SerializedName("vcahospitals_url")
    @Expose
    private String vcahospitalsUrl;
    @SerializedName("temperament")
    @Expose
    private String temperament;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("country_codes")
    @Expose
    private String countryCodes;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("indoor")
    @Expose
    private int indoor;
    @SerializedName("lap")
    @Expose
    private int lap;
    @SerializedName("alt_names")
    @Expose
    private String altNames;
    @SerializedName("adaptability")
    @Expose
    private int adaptability;
    @SerializedName("affection_level")
    @Expose
    private int affectionLevel;
    @SerializedName("child_friendly")
    @Expose
    private int childFriendly;
    @SerializedName("dog_friendly")
    @Expose
    private int dogFriendly;
    @SerializedName("energy_level")
    @Expose
    private int energyLevel;
    @SerializedName("grooming")
    @Expose
    private int grooming;
    @SerializedName("health_issues")
    @Expose
    private int healthIssues;
    @SerializedName("intelligence")
    @Expose
    private int intelligence;
    @SerializedName("shedding_level")
    @Expose
    private int sheddingLevel;
    @SerializedName("social_needs")
    @Expose
    private int socialNeeds;
    @SerializedName("stranger_friendly")
    @Expose
    private int strangerFriendly;
    @SerializedName("vocalisation")
    @Expose
    private int vocalisation;
    @SerializedName("experimental")
    @Expose
    private int experimental;
    @SerializedName("hairless")
    @Expose
    private int hairless;
    @SerializedName("natural")
    @Expose
    private int natural;
    @SerializedName("rare")
    @Expose
    private int rare;
    @SerializedName("rex")
    @Expose
    private int rex;
    @SerializedName("suppressed_tail")
    @Expose
    private int suppressedTail;
    @SerializedName("short_legs")
    @Expose
    private int shortLegs;
    @SerializedName("wikipedia_url")
    @Expose
    private String wikipediaUrl;
    @SerializedName("hypoallergenic")
    @Expose
    private int hypoallergenic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCfaUrl() {
        return cfaUrl;
    }

    public void setCfaUrl(String cfaUrl) {
        this.cfaUrl = cfaUrl;
    }

    public String getVetstreetUrl() {
        return vetstreetUrl;
    }

    public void setVetstreetUrl(String vetstreetUrl) {
        this.vetstreetUrl = vetstreetUrl;
    }

    public String getVcahospitalsUrl() {
        return vcahospitalsUrl;
    }

    public void setVcahospitalsUrl(String vcahospitalsUrl) {
        this.vcahospitalsUrl = vcahospitalsUrl;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(String countryCodes) {
        this.countryCodes = countryCodes;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public int getIndoor() {
        return indoor;
    }

    public void setIndoor(int indoor) {
        this.indoor = indoor;
    }

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public String getAltNames() {
        return altNames;
    }

    public void setAltNames(String altNames) {
        this.altNames = altNames;
    }

    public int getAdaptability() {
        return adaptability;
    }

    public void setAdaptability(int adaptability) {
        this.adaptability = adaptability;
    }

    public int getAffectionLevel() {
        return affectionLevel;
    }

    public void setAffectionLevel(int affectionLevel) {
        this.affectionLevel = affectionLevel;
    }

    public int getChildFriendly() {
        return childFriendly;
    }

    public void setChildFriendly(int childFriendly) {
        this.childFriendly = childFriendly;
    }

    public int getDogFriendly() {
        return dogFriendly;
    }

    public void setDogFriendly(int dogFriendly) {
        this.dogFriendly = dogFriendly;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public int getGrooming() {
        return grooming;
    }

    public void setGrooming(int grooming) {
        this.grooming = grooming;
    }

    public int getHealthIssues() {
        return healthIssues;
    }

    public void setHealthIssues(int healthIssues) {
        this.healthIssues = healthIssues;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getSheddingLevel() {
        return sheddingLevel;
    }

    public void setSheddingLevel(int sheddingLevel) {
        this.sheddingLevel = sheddingLevel;
    }

    public int getSocialNeeds() {
        return socialNeeds;
    }

    public void setSocialNeeds(int socialNeeds) {
        this.socialNeeds = socialNeeds;
    }

    public int getStrangerFriendly() {
        return strangerFriendly;
    }

    public void setStrangerFriendly(int strangerFriendly) {
        this.strangerFriendly = strangerFriendly;
    }

    public int getVocalisation() {
        return vocalisation;
    }

    public void setVocalisation(int vocalisation) {
        this.vocalisation = vocalisation;
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

    public int getNatural() {
        return natural;
    }

    public void setNatural(int natural) {
        this.natural = natural;
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

    public int getSuppressedTail() {
        return suppressedTail;
    }

    public void setSuppressedTail(int suppressedTail) {
        this.suppressedTail = suppressedTail;
    }

    public int getShortLegs() {
        return shortLegs;
    }

    public void setShortLegs(int shortLegs) {
        this.shortLegs = shortLegs;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public void setWikipediaUrl(String wikipediaUrl) {
        this.wikipediaUrl = wikipediaUrl;
    }

    public int getHypoallergenic() {
        return hypoallergenic;
    }

    public void setHypoallergenic(int hypoallergenic) {
        this.hypoallergenic = hypoallergenic;
    }
}

