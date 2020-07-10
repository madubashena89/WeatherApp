package top.stores.weatherapp.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import top.stores.weatherapp.repositories.WeatherRepository;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class MainActivityViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository;
    private LiveData<List<WeatherReportEntity>> weatherList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        weatherRepository = new WeatherRepository(application);
    }

    public LiveData<List<WeatherReportEntity>> getWeatherList() {
        weatherList = weatherRepository.getWeatherList();
        return weatherList;
    }

    public LiveData<List<WeatherReportEntity>> getWeatherDetailsByID(int weatherID){
        weatherList = weatherRepository.getWeatherDetailsByID(weatherID);
        return weatherList;
    }
}
