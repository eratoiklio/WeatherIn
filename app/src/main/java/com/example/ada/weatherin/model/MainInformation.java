package com.example.ada.weatherin.model;

import com.squareup.moshi.Json;

/**
 * Created by Ada on 2016-11-13.
 */

public class MainInformation {
    Double temp;
    Integer humidity;
    @Json(name = "temp_min")
    Double tempMin;
    @Json(name = "temp_max")
    Double tempMax;

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }
};


