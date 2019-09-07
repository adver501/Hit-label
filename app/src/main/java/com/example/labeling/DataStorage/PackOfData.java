package com.example.labeling.DataStorage;

import java.io.Serializable;
import io.realm.RealmList;
import io.realm.RealmObject;

public class PackOfData extends RealmObject implements Serializable {

    public RealmList<Text_> text = new RealmList<>();
    public RealmList<ImageUri_> imageUri = new RealmList<>();
    public RealmList<VideoUri_> videoUri = new RealmList<>();
    public RealmList<Labels_> labels = new RealmList<>();
    public NoPack noPack;
    public int noPack_;

    //constructor
    public PackOfData() {
    }

    //getter-setters
    public RealmList<Text_> getText() {
        return text;
    }

    public void setText(RealmList<Text_> text) {
        this.text = text;
    }

    public RealmList<ImageUri_> getImageUri() {
        return imageUri;
    }

    public void setImageUri(RealmList<ImageUri_> imageUri) {
        this.imageUri = imageUri;
    }

    public RealmList<VideoUri_> getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(RealmList<VideoUri_> videoUri) {
        this.videoUri = videoUri;
    }

    public RealmList<Labels_> getLabels() {
        return labels;
    }

    public void setLabels(RealmList<Labels_> labels) {
        this.labels = labels;
    }

    public NoPack getNoPack() {
        return noPack;
    }

    public void setNoPack(NoPack noPack) {
        this.noPack = noPack;
        noPack_ = noPack.getNoPack();
    }
}
