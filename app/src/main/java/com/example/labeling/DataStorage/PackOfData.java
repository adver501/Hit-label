package com.example.labeling.DataStorage;

import java.io.Serializable;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PackOfData extends RealmObject implements Serializable {

    public RealmList<Text_> text = new RealmList<>();
    public RealmList<ImageUri_> imageUri = new RealmList<>();
    public RealmList<VideoUri_> videoUri = new RealmList<>();
//    public RealmList<Labels_> labels = new RealmList<>();
    public RealmList<CommonUserLabelSelected> comUsrLabelsSelected = new RealmList<>();
    public RealmList<MasterUserLabelSelected> MasUsrLabelsSelected = new RealmList<>();
    public NoPack noPack;
    @PrimaryKey
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

    public RealmList<CommonUserLabelSelected> getComUsrLabelsSelected() {
        return comUsrLabelsSelected;
    }

    public void setComUsrLabelsSelected(RealmList<CommonUserLabelSelected> comUsrLabelsSelected) {
        this.comUsrLabelsSelected = comUsrLabelsSelected;
    }

    public RealmList<MasterUserLabelSelected> getMasUsrLabelsSelected() {
        return MasUsrLabelsSelected;
    }

    public void setMasUsrLabelsSelected(RealmList<MasterUserLabelSelected> masUsrLabelsSelected) {
        MasUsrLabelsSelected = masUsrLabelsSelected;
    }

    public NoPack getNoPack() {
        return noPack;
    }

    public void setNoPack(NoPack noPack) {
        this.noPack = noPack;
//        noPack_ = noPack.getNoPack();
    }

}
