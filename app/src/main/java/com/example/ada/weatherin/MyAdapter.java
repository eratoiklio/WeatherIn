package com.example.ada.weatherin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.example.ada.weatherin.model.BasicInfo;

/**
 * Created by Ada on 2016-11-21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<BasicInfo> mDataSet;
    private MainActivity mainActivity;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView temperatureTV;
        public ImageView iconIV;
        public TextView cityTV;
        public TextView descriptionTV;
        MainActivity ma;

        public ViewHolder(View v, MainActivity mainActivity) {
            super(v);
            temperatureTV = (TextView) v.findViewById(R.id.temperature_tv);
            iconIV = (ImageView) v.findViewById(R.id.weather_icon_view);
            cityTV = (TextView) v.findViewById(R.id.city_name_tv);
            descriptionTV = (TextView) v.findViewById(R.id.weather_description);
            ma = mainActivity;
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = getLayoutPosition();
                    ma.removeElement(pos);
                    return true;
                }
            });
        }

    }

    public MyAdapter(List myDataSet, MainActivity mainActivity) {
        mDataSet = myDataSet;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tiles_layout, parent, false);

        ViewHolder vh = new ViewHolder(v, mainActivity);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.temperatureTV.setText(mainActivity.getString(R.string.temperature, mDataSet.get(position).getTemperature()));
        //mDataSet.get(position).getTemperature() + " °C")
        String imageName = mDataSet.get(position).getUrl();

        holder.iconIV.setImageResource(imageId(imageName));
        holder.cityTV.setText(mDataSet.get(position).getCityName());
        holder.descriptionTV.setText(mDataSet.get(position).getWeatherDesc());
    }

    int imageId(String urlFragment) {
        String properName = "icon" + urlFragment;
        return mainActivity.getResources().getIdentifier(properName, "drawable", mainActivity.getPackageName());
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
