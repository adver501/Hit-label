package com.example.labeling;

import io.realm.RealmObject;

public class NoPack extends RealmObject {
    public int noPack;

    public int getNoPack() {
        return noPack;
    }

    public void setNoPack(int noPack) {
        this.noPack = noPack;
    }
}
