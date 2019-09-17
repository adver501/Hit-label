package com.example.labeling.DataStorage;

import io.realm.RealmObject;

public class MasterUserLabelSelected extends RealmObject {
    private String MLabels;

    //getter-setters

    public String getMLabels() {
        return MLabels;
    }

    public void setMLabels(String MLabels) {
        this.MLabels = MLabels;
    }
}
