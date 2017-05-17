package com.example.ada.weatherin.model;

import com.squareup.moshi.Json;

/**
 * Created by Ada on 2016-11-13.
 */

public class WeatherModel {
    private MainInformation main;
    private WeatherInformation[] weather;
    @Json(name = "name")
    private String cityName;

    public MainInformation getMain() {
        return main;
    }

    public void setMain(MainInformation main) {
        this.main = main;
    }

    public WeatherInformation[] getWeather() {
        return weather;
    }

    public void setWeather(WeatherInformation[] weather) {
        this.weather = weather;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

