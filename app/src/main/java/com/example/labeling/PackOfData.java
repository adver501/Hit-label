package com.example.labeling;

import java.io.Serializable;
import java.util.ArrayList;

public class PackOfData implements Serializable {
    private String[] text;
    private String[] imageUri;
    private String[] videoUri;
    private String[] labels;
    //constructor
    public PackOfData() {
    }
    //getter-setters
    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public String[] getImageUri() {
        return imageUri;
    }

    public void setImageUri(String[] imageUri) {
        this.imageUri = imageUri;
    }

    public String[] getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String[] videoUri) {
        this.videoUri = videoUri;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}
