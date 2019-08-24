package com.example.labeling;

public class Item {

    private String label;
    private String addLabelBtn;
    private String deleteBtn;
    private String saveLabelsBtn;


    public Item(String Label, String addLabelBtn, String deleteBtn, String saveLabelsBtn) {
        this.label = Label;
        this.addLabelBtn = addLabelBtn;
        this.deleteBtn = deleteBtn;
        this.saveLabelsBtn = saveLabelsBtn;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDeleteBtn() {
        return deleteBtn;
    }

    public void setDeleteBtn(String deleteBtn) {
        this.deleteBtn = deleteBtn;
    }

    public String getAddLabelBtn() {
        return addLabelBtn;
    }

    public void setAddLabelBtn(String addLabelBtn) {
        this.addLabelBtn = addLabelBtn;
    }

    public String getSaveLabelsBtn() {
        return saveLabelsBtn;
    }

    public void setSaveLabelsBtn(String saveLabelsBtn) {
        this.saveLabelsBtn = saveLabelsBtn;
    }
}