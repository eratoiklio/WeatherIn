package com.example.ada.weatherin;

import com.example.ada.weatherin.model.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ada on 2016-11-14.
 */

public interface WeatherClient {
    @GET("weather")
    Call<WeatherModel>getWeather(@Query("q")String city, @Query("APPID") String key);
}
