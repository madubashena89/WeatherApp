package top.stores.weatherapp.adapters;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import top.stores.weatherapp.R;
import top.stores.weatherapp.databinding.ItemCardBinding;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherViewHolder extends RecyclerView.ViewHolder{
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
