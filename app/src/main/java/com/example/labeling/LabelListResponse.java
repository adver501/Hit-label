package com.example.labeling;

import com.example.labeling.DataStorage.Labels_;
import com.example.labeling.reqAndRes.FailedSuccessResponse;

import java.util.List;

public class LabelListResponse extends FailedSuccessResponse {
    private List<Labels_> labelQ;

    public List<Labels_> getLabelQ() {
        return labelQ;
    }

    public void setLabelQ(List<Labels_> labelQ) {
        this.labelQ = labelQ;
    }
}