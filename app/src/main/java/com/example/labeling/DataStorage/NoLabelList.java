package com.example.labeling.DataStorage;

import io.realm.RealmObject;

public class NoLabelList extends RealmObject {
    private int noList;

    public int getNoList() {
        return noList;
    }

    public void setNoList(int noList) {
        this.noList = noList;
    }
}
