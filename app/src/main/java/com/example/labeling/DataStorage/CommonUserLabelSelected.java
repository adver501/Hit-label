package com.example.labeling.DataStorage;

import io.realm.RealmObject;

public class CommonUserLabelSelected extends RealmObject {
    private String CLabels;
    private int CLabelCounter = 0;

    public int getCLabelCounter() {
        return CLabelCounter;
    }

    public void setCLabelCounter(int CLabelCounter) {
        this.CLabelCounter = CLabelCounter;
    }

    public String getCLabels() {
        return CLabels;
    }

    public void setCLabels(String CLabels) {
        this.CLabels = CLabels;
    }
}
