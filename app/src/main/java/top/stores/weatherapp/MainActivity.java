package top.stores.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_city_ID, btn_getWeattherByID, btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_watherReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_city_ID = findViewById(R.id.btn_getCityID);
        btn_getWeattherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_watherReport = findViewById(R.id.lv_weather);
        final WeatherDataSeervice weatherDataSeervice = new WeatherDataSeervice(MainActivity.this);



        btn_city_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //this didn't returned anything.
                 weatherDataSeervice.getCityID(et_dataInput.getText().toString(), new WeatherDataSeervice.VolleyResponseListner() {
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




        btn_getWeattherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              weatherDataSeervice.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataSeervice.ForecastByIDResponse() {
             @Override
             public void onError(String message) {
                 Toast.makeText(MainActivity.this,"Something went wrong..!", Toast.LENGTH_SHORT).show();

             }

             @Override
             public void onResponse(List<WeatherReportModel> weatherReportModels) {
                // Toast.makeText(MainActivity.this,weatherReportModels.toString(), Toast.LENGTH_SHORT).show();

                 ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                 lv_watherReport.setAdapter(arrayAdapter);


             }
         });

            }
        });




        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                weatherDataSeervice.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataSeervice.GetCityByForecastByNameCallBack() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something went wrong..!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        // Toast.makeText(MainActivity.this,weatherReportModels.toString(), Toast.LENGTH_SHORT).show();

                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportModels);
                        lv_watherReport.setAdapter(arrayAdapter);


                    }
                });


            }
        });

    }
}
