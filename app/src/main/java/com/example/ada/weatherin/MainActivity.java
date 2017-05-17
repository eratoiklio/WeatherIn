package com.example.ada.weatherin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ada.weatherin.model.BasicInfo;
import com.example.ada.weatherin.model.WeatherModel;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    static final String APPID = "insert your key here";
    static final String SAVE_STATE = "save state";

    WeatherClient weatherClient;
    EditText cityNameET;
    ImageView sentRequestButton;
    private RecyclerView.Adapter myAdapter;
    List<BasicInfo> dataAboutWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataAboutWeather = new ArrayList<>();
        cityNameET = (EditText) findViewById(R.id.city_name_et);
        sentRequestButton = (ImageView) findViewById(R.id.sent_request_button);
        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager myLayoutManager = new GridLayoutManager(this, 2);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new MyAdapter(dataAboutWeather, this);
        myRecyclerView.setAdapter(myAdapter);

        weatherClient = RetrofitFactory.getRetrofit().create(WeatherClient.class);

        sentRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cityNameET.getText().toString();
                if (!TextUtils.isEmpty(name)) {
                    sentRequest(name);
                    cityNameET.setText("");
                }
            }
        });
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .build();
        ImageLoader.getInstance().init(config);
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> s = new HashSet<>();
        for (int i = 0; i < dataAboutWeather.size(); i++) {
            s.add(dataAboutWeather.get(i).getCityName());
        }
        editor.putStringSet(SAVE_STATE, s).apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        Set s = sharedPreferences.getStringSet(SAVE_STATE, null);
        if (s == null)
            return;
        Iterator iter = s.iterator();
        dataAboutWeather.clear();
        while (iter.hasNext()) {
            sentRequest(iter.next().toString());
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void removeElement(int pos) {

        dataAboutWeather.remove(pos);
        Toast.makeText(this, R.string.delete_alert, Toast.LENGTH_SHORT).show();
        myAdapter.notifyDataSetChanged();
    }


    void sentRequest(String cityName) {

        final Call<WeatherModel> request = weatherClient.getWeather(cityName, APPID);
        request.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.body() == null) {
                    Toast.makeText(MainActivity.this, R.string.body_error, Toast.LENGTH_SHORT).show();
                    return;
                }
                Long t = Math.round(response.body().getMain().getTemp() - 273.15);
                String urlFragment = response.body().getWeather()[0].getWeatherIcon();

                dataAboutWeather.add(new BasicInfo(t, urlFragment, response.body().getWeather()[0].getDescription(),
                        response.body().getCityName()));
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.e("MainActivity", "error", t);
                if (t instanceof UnknownHostException)
                    Toast.makeText(MainActivity.this, R.string.no_internet_error, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, R.string.api_error, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
