package top.stores.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.stores.weatherapp.R;
import top.stores.weatherapp.databinding.ItemCardBinding;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder>{
     List<WeatherReportEntity> weatherReportEntities = new ArrayList<>();

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemCardBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_card,parent,false);
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        //WeatherReportEntity watherEntity = weatherReportEntities.get(position);
        holder.itemCardBinding.tvState.setText(weatherReportEntities.get((position)).getWeatherStateName());
        holder.itemCardBinding.tvDate.setText(weatherReportEntities.get(position).getApplicableDate());
        holder.itemCardBinding.tvLovwTemp.setText(String.valueOf(weatherReportEntities.get(position).getMinTemp()));
        holder.itemCardBinding.tvHighTemp.setText(String.valueOf(weatherReportEntities.get(position).getMaxTemp()));
        holder.itemCardBinding.tvTemp.setText(String.valueOf(weatherReportEntities.get(position).getTheTemp()));
        holder.itemCardBinding.tvWindSpeed.setText(String.valueOf(weatherReportEntities.get(position).getWindSpeed()));
        holder.itemCardBinding.tvAirPressure.setText(String.valueOf(weatherReportEntities.get(position).getAirPressure()));
        holder.setImage(weatherReportEntities, position);
    }

    @Override
    public int getItemCount() {
        return weatherReportEntities.size();
    }


    public void setWeatherReportEntities(List<WeatherReportEntity> weatherReportEntities){
          this.weatherReportEntities = weatherReportEntities;
          notifyDataSetChanged();
    }
}


class WeatherViewHolder extends RecyclerView.ViewHolder{
    public ItemCardBinding itemCardBinding;

    public WeatherViewHolder(ItemCardBinding itemCardBinding) {
        super(itemCardBinding.getRoot());
        this.itemCardBinding = itemCardBinding;
    }

    public void setImage(List<WeatherReportEntity> weatherReportEntities, int position){
        String state = weatherReportEntities.get(position).getWeatherStateAbbr();
        switch (state){
            case "sn" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_sn);
                 break;
            case "sl" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_sl);
                break;
            case "h" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_h);
                break;
            case "t" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_t);
                break;
            case "hr" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_hr);
                break;
            case "lr" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_lr);
                break;
            case "s" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_s);
                break;
            case "hc" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_hc);
                break;
            case "lc" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_lc);
                break;
            case "c" : itemCardBinding.weatherImage.setImageResource(R.drawable.ic_c);
                break;

        }

    }

//    public void bind(WeatherReportEntity watherEntity) {
//        itemCardBinding.tvState.setText(watherEntity.getWeatherStateName());
//        itemCardBinding.tvDate.setText(watherEntity.getApplicableDate());
//        itemCardBinding.tvLovwTemp.append(watherEntity.getMinTemp());
//
////        itemCardBinding.setVariable(BR.model, obj);
////        itemCardBinding.executePendingBindings();
//    }
}

