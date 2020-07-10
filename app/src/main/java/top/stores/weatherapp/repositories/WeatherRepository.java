package top.stores.weatherapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import top.stores.weatherapp.roomDb.WeatherDao;
import top.stores.weatherapp.roomDb.WeatherDataBase;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherRepository {
    private WeatherDao weatherDao;
    private LiveData<List<WeatherReportEntity>> weatherList;

    public WeatherRepository(Application application) {
        WeatherDataBase weatherDataBase = WeatherDataBase.getInstance(application);
        weatherDao = weatherDataBase.weatherDao();
    }

    public LiveData<List<WeatherReportEntity>> getWeatherList() {
        return weatherDao.getAllWeatherDetails();
    }

    public LiveData<List<WeatherReportEntity>> getWeatherDetailsByID(int weatherID){
        return weatherDao.getAllWeatherDetailsById(weatherID);
    }
}
