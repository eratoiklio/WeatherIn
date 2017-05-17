package com.example.ada.weatherin.model;

import com.squareup.moshi.Json;

/**
 * Created by Ada on 2016-11-20.
 */

public class WeatherInformation {
    @Json(name = "icon")
    String weatherIcon;
    String description;

    public WeatherInformation(String weatherIcon, String description) {
        this.weatherIcon = weatherIcon;
        this.description = description;
    }


    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
