package com.example.labeling.DataStorage;

import io.realm.RealmObject;

public class Labels_ extends RealmObject {
    private String labels;

    //getter-setters
    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }
}
