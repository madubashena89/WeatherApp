package top.stores.weatherapp.repositories;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public List<WeatherReportEntity> getWeatherList() {
        return weatherDao.getAllWeatherDetails();
    }

    public LiveData<List<WeatherReportEntity>> getWeatherDetailsByID(int weatherID){
        return weatherDao.getAllWeatherDetailsById(weatherID);
    }

}
