
package com.example.dogapp.model;

import com.google.gson.annotations.SerializedName;

public class DogBreed {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("life_span")
    private String lifeSpan;

    @SerializedName("origin")
    private String origin;

    @SerializedName("url")
    private String url;

    @SerializedName("bred_for")
    private String bredfor;

    private String like;

    public DogBreed(int id, String name, String lifeSpan, String origin, String url, String bredfor){
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.bredfor = bredfor;
        this.like = "NO";
    }

    public String getBredfor() {
        return bredfor;
    }
    public void setBredfor(String bredfor) {
        this.bredfor = bredfor;
    }
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLifeSpan() {
        return lifeSpan;
    }
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}