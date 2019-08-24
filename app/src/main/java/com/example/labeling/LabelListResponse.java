package com.example.labeling;

import com.example.labeling.reqAndRes.FailedSuccessResponse;

import java.util.List;

public class LabelListResponse extends FailedSuccessResponse {
    private List<AllLabels> labelQ;

    public List<AllLabels> getLabelQ() {
        return labelQ;
    }

    public void setLabelQ(List<AllLabels> labelQ) {
        this.labelQ = labelQ;
    }
}