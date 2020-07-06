package top.stores.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.List;

import top.stores.weatherapp.databinding.ActivityMainBinding;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);


        final WeatherDataSeervice weatherDataSeervice = new WeatherDataSeervice(MainActivity.this);



        binding.btnGetCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this didn't returned anything.
                 weatherDataSeervice.getCityID(binding.etDataInput.getText().toString(), new WeatherDataSeervice.VolleyResponseListner() {
                     @Override
                     public void onError(String message) {

                         Toast.makeText(MainActivity.this, "Something wrong ", Toast.LENGTH_SHORT).show();

                     }

                     @Override
                     public void onResponse(String cityID) {
                         Toast.makeText(MainActivity.this, "Returned an ID of "+ cityID, Toast.LENGTH_SHORT).show();

                     }
                 });

            }

        });




        binding.btnGetWeatherByCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              weatherDataSeervice.getCityForecastByID(binding.etDataInput.getText().toString(), new WeatherDataSeervice.ForecastByIDResponse() {
             @Override
             public void onError(String message) {
                 Toast.makeText(MainActivity.this,"Something went wrong..!", Toast.LENGTH_SHORT).show();

             }

             @Override
             public void onResponse(List<WeatherReportEntity> weatherReportEntities) {
                // Toast.makeText(MainActivity.this,weatherReportEntities.toString(), Toast.LENGTH_SHORT).show();

                 ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportEntities);
                 binding.lvWeather.setAdapter(arrayAdapter);


             }
         });

            }
        });




        binding.btnGetWeatherByCityName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                weatherDataSeervice.getCityForecastByName(binding.etDataInput.getText().toString(), new WeatherDataSeervice.GetCityByForecastByNameCallBack() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something went wrong..!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportEntity> weatherReportEntities) {
                        // Toast.makeText(MainActivity.this,weatherReportEntities.toString(), Toast.LENGTH_SHORT).show();

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportEntities);
                        binding.lvWeather.setAdapter(arrayAdapter);


                    }
                });


            }
        });

    }
}
