package com.example.jsonvolley_ckk;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;

public class VideoGame {
    @SerializedName("name")
    public String videoGameName;
    @SerializedName("year")
    public int videoGameYearReleased;
    @SerializedName("description")
    public String videoGameDescription;

    public String getVideoGameName() {return videoGameName;}

    public int getVideoGameYearReleased() {return videoGameYearReleased;}

    public String getVideoGameDescription() {return videoGameDescription;}

    public VideoGame(String name, int year, String description) {
        videoGameName = name;
        videoGameYearReleased = year;
        videoGameDescription = description;
    }
}
