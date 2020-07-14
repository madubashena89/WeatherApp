package top.stores.weatherapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

import top.stores.weatherapp.R;
import top.stores.weatherapp.databinding.ItemCardBinding;
import top.stores.weatherapp.repositories.WeatherRepository;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class MainActivityViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;
    private MutableLiveData<List<WeatherReportEntity>> weatherList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        weatherRepository = new WeatherRepository(application);
    }

    public MutableLiveData<List<WeatherReportEntity>> getWeatherList() {
        weatherList.setValue(weatherRepository.getWeatherList());
        return weatherList;
    }

//    public MutableLiveData<List<WeatherReportEntity>>  getWeatherDetailsByID(int weatherID){
//        weatherList.setValue(weatherRepository.getWeatherDetailsByID(weatherID));
//        return weatherList;
//    }





}
