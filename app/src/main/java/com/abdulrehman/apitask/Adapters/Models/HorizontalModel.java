package com.abdulrehman.apitask.Adapters.Models;

public class HorizontalModel {

    String mImageUrl, name, description;

    public HorizontalModel(String imageUrl) {

        mImageUrl = imageUrl;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
