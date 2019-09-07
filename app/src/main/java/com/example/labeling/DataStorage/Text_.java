package com.example.labeling.DataStorage;

import io.realm.RealmObject;

public class Text_ extends RealmObject {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
