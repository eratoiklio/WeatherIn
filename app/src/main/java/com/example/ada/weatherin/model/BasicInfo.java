package com.example.ada.weatherin.model;

import com.squareup.moshi.Json;

import java.io.Serializable;

/**
 * Created by Ada on 2016-11-21.
 */

public class BasicInfo implements Serializable {
    private Long temperature;
    private String url;
    @Json(name = "weather_desc")
    private String weatherDesc;
    @Json(name = "city_name")
    private String cityName;

    public BasicInfo(Long temperature, String url, String weatherDesc, String cityName) {
        this.temperature = temperature;
        this.url = url;
        this.weatherDesc = weatherDesc;
        this.cityName = cityName;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String city_name) {
        this.cityName = cityName;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
