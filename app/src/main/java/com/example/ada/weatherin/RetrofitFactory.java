package com.example.ada.weatherin;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Ada on 2016-11-14.
 */

public class RetrofitFactory {
    static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/";
    static Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create()).build();

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
