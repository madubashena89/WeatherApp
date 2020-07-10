package top.stores.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.List;

import top.stores.weatherapp.adapters.WeatherAdapter;
import top.stores.weatherapp.databinding.ActivityMainBinding;
import top.stores.weatherapp.databinding.ActivityMainRecylerViewBinding;
import top.stores.weatherapp.roomDb.WeatherReportEntity;
import top.stores.weatherapp.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainRecylerViewBinding binding;
    private MainActivityViewModel viewModel;
    private WeatherAdapter weatherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_recyler_view);

        final WeatherDataSeervice weatherDataSeervice = new WeatherDataSeervice(MainActivity.this);

        // bind RecyclerView
        final RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        weatherAdapter = new WeatherAdapter();
//        recyclerView.setAdapter(weatherAdapter);
//        getWeather();


       //RecyclerView
     /*   private void populateData() {
            List<DataModel> dataModelList = new ArrayList<>();

            dataModelList.add(new DataModel("Android Oreo", "8.1"));
            dataModelList.add(new DataModel("Android Pie", "9.0"));
            dataModelList.add(new DataModel("Android Nougat", "7.0"));
            dataModelList.add(new DataModel("Android Marshmallow", "6.0"));

            MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(dataModelList, this);
            binding.setMyAdapter(myRecyclerViewAdapter);
        }*/




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

                // ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportEntities);
                // binding.lvWeather.setAdapter(arrayAdapter);
                 binding.recyclerView.setAdapter(weatherAdapter);
                 getWeather();

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

                      //  ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportEntities);
                        weatherAdapter.setWeatherReportEntities(weatherReportEntities);
                        binding.recyclerView.setAdapter(weatherAdapter);
                      //  getWeather();

                    }
                });


            }
        });

    }


    private void getWeather(){
        viewModel.getWeatherList().observe(this, new Observer<List<WeatherReportEntity>>() {
            @Override
            public void onChanged(List<WeatherReportEntity> weatherReportEntities) {
                weatherAdapter.setWeatherReportEntities(weatherReportEntities);
            }
        });
    }


    private void getWeatherByID(int weatherID){
        viewModel.getWeatherDetailsByID(weatherID).observe(this, new Observer<List<WeatherReportEntity>>() {
            @Override
            public void onChanged(List<WeatherReportEntity> weatherReportEntities) {
                weatherAdapter.setWeatherReportEntities(weatherReportEntities);
            }
        });
    }
}
