package top.stores.weatherapp.roomDb;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {WeatherReportEntity.class}, version = 1)
public abstract class WeatherDataBase extends RoomDatabase {

    public abstract WeatherDao weatherDao();

    private static WeatherDataBase instance;

    public static synchronized WeatherDataBase getInstance(Context context){

      if(instance == null) {
          instance = Room.databaseBuilder(context.getApplicationContext(),
                  WeatherDataBase.class, "weather_db")
                  .fallbackToDestructiveMigration()
                  .build();
      }
       return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void>{

        private WeatherDao weatherDao;



        public  InitialDataAsyncTask(WeatherDataBase weatherDataBase){
            weatherDao = weatherDataBase.weatherDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
