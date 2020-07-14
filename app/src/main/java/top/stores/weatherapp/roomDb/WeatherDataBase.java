package top.stores.weatherapp.roomDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Database(entities = {WeatherReportEntity.class}, version = 1)
public abstract class WeatherDataBase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

    private static WeatherDataBase instance;

    public static synchronized WeatherDataBase getInstance(Context context){

      if(instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(),
                  WeatherDataBase.class, "weather_db")
                  .fallbackToDestructiveMigration()
                  .addCallback(callback)
                  .build();
      }
       return instance;
    }


   private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
       @Override
       public void onCreate(@NonNull SupportSQLiteDatabase db) {
           super.onCreate(db);
           new InitialDataAsyncTask(instance).execute();
       }
   };


    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, List<WeatherReportEntity>> {

        private WeatherDao weatherDao;



        public  InitialDataAsyncTask(WeatherDataBase weatherDataBase){
            weatherDao = weatherDataBase.weatherDao();
        }

        @Override
        protected List<WeatherReportEntity> doInBackground(Void... voids) {
           return weatherDao.getAllWeatherDetails();

        }
    }

    private void testInsertDB(){
        final List<WeatherReportEntity> weatherReportEntities = new ArrayList<>();

        WeatherReportEntity oneDayWeather = new WeatherReportEntity();

        oneDayWeather.setId(5);
        oneDayWeather.setWeatherStateName("weather_state_name");
        oneDayWeather.setWeatherStateAbbr("weather_state_abbr");
        oneDayWeather.setWindDirectionCompass("wind_direction_compass");
        oneDayWeather.setCreated("created");
        oneDayWeather.setApplicableDate("applicable_date");
        oneDayWeather.setMinTemp(90);
        oneDayWeather.setMaxTemp(70);
        oneDayWeather.setTheTemp(60);
        oneDayWeather.setWindSpeed(80);
        oneDayWeather.setWindDirection(90);
        oneDayWeather.setAirPressure(89);
        oneDayWeather.setHumidity(89);
        oneDayWeather.setVisibility(90);
        oneDayWeather.setPredictability(70);
        weatherReportEntities.add(oneDayWeather);
        weatherDao().insert(oneDayWeather);
    }


}
