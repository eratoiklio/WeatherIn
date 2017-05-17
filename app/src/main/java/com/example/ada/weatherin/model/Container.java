package com.example.ada.weatherin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ada on 2016-12-27.
 */

public class Container implements Serializable {
    List<BasicInfo> basicInfoList;

    public Container(List<BasicInfo> basicInfoList) {
        this.basicInfoList = basicInfoList;
    }

    public List<BasicInfo> getBasicInfoList() {
        return basicInfoList;
    }

    public void setBasicInfoList(List<BasicInfo> basicInfoList) {
        this.basicInfoList = basicInfoList;
    }


}
