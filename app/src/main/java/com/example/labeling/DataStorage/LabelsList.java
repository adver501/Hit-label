package com.example.labeling.DataStorage;

import io.realm.RealmList;
import io.realm.RealmObject;

public class LabelsList extends RealmObject {
    private RealmList<Labels_> labels = new RealmList<>();
    private NoLabelList noLabelList;
    private int noList_;

    public RealmList<Labels_> getLabels() {
        return labels;
    }

    public void setLabels(RealmList<Labels_> labels) {
        this.labels = labels;
    }

    public NoLabelList getNoLabelList() {
        return noLabelList;
    }

    public void setNoLabelList(NoLabelList noLabelList) {
        this.noLabelList = noLabelList;
        this.noList_ = noLabelList.getNoList();
    }
}
