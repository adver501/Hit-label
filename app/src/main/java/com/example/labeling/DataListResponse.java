package com.example.labeling;

import com.example.labeling.DataStorage.PackOfData;
import com.example.labeling.reqAndRes.FailedSuccessResponse;

import java.util.List;

public class DataListResponse extends FailedSuccessResponse {
    private List<PackOfData> DataQ;

    public List<PackOfData> getDataQ() {
        return DataQ;
    }

    public void setDataQ(List<PackOfData> dataQ) {
        this.DataQ = dataQ;
    }
}