package top.stores.weatherapp.roomDb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WeatherDao {

    @Insert
    void insert (WeatherReportEntity weatherEntity);

    @Update
    void update (WeatherReportEntity weatherEntity);

    @Delete
    void delete (WeatherReportEntity weatherEntity);

    @Query("SELECT * FROM weather_table")
    List<WeatherReportEntity> getAllWeatherDetails();

    @Query("SELECT * FROM weather_table WHERE id==:weatherID")
    LiveData<List<WeatherReportEntity>> getAllWeatherDetailsById(int weatherID);

}
