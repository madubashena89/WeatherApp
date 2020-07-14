package top.stores.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import top.stores.weatherapp.adapters.WeatherAdapter;
import top.stores.weatherapp.adapters.WeatherViewHolder;
import top.stores.weatherapp.asynctasks.HttpGetRequest;
import top.stores.weatherapp.databinding.ActivityMainBinding;
import top.stores.weatherapp.databinding.ActivityMainRecylerViewBinding;
import top.stores.weatherapp.databinding.ItemCardBinding;
import top.stores.weatherapp.repositories.WeatherRepository;
import top.stores.weatherapp.roomDb.WeatherDao;
import top.stores.weatherapp.roomDb.WeatherDataBase;
import top.stores.weatherapp.roomDb.WeatherReportEntity;
import top.stores.weatherapp.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainRecylerViewBinding binding;
    private MainActivityViewModel viewModel;
    private WeatherAdapter weatherAdapter;
    String cityID;
    final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";

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

     /*   WeatherDataBase db = WeatherDataBase.getInstance(this);




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
            //weatherDao().insert(oneDayWeather);
        db.weatherDao().insert(oneDayWeather);
        Log.d("DBVal","db.weatherDao().getAllWeatherDetails()" );*/
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
             public void onResponse(List<WeatherReportEntity> weatherReportEntities) throws ExecutionException, InterruptedException {
                // Toast.makeText(MainActivity.this,weatherReportEntities.toString(), Toast.LENGTH_SHORT).show();

                // ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weatherReportEntities);
                // binding.lvWeather.setAdapter(arrayAdapter);
                 weatherAdapter.setWeatherReportEntities(weatherReportEntities);
                 binding.recyclerView.setAdapter(weatherAdapter);
                // getWeather();

             }
         });

            }
        });




        binding.btnGetWeatherByCityName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
/*                HttpGetRequest getRequest = new HttpGetRequest(getApplicationContext());
                getRequest.execute(binding.etDataInput.getText().toString());
             //  viewModel.parseWeatherPayloadToRepositoryByName(binding.etDataInput.getText().toString());
                //paerseResponseToWeatherEntityByCityName(binding.etDataInput.getText().toString());
                binding.recyclerView.setAdapter(weatherAdapter);
                try {
                    getWeather();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/


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
                       // holder.itemCardBinding.cityName.setText(binding.etDataInput.toString());
                      //  getWeather();

                    }
                });


            }
        });

    }


    private void getWeather() throws ExecutionException, InterruptedException {
        viewModel.getWeatherList().observe(this, new Observer<List<WeatherReportEntity>>() {
            @Override
            public void onChanged(List<WeatherReportEntity> weatherReportEntities) {
                weatherAdapter.setWeatherReportEntities(weatherReportEntities);

            }
        });
    }


//    private void getWeatherByID(int weatherID){
//        viewModel.getWeatherDetailsByID(weatherID).observe(this, new Observer<List<WeatherReportEntity>>() {
//            @Override
//            public void onChanged(List<WeatherReportEntity> weatherReportEntities) {
//                weatherAdapter.setWeatherReportEntities(weatherReportEntities);
//            }
//        });
//    }

    public String getCityIdByityName(String cityName) {
        String url = QUERY_FOR_CITY_ID + cityName;

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");
                    paerseResponseToWeatherEntityByCityID(cityID);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // this worked, but it didn't return the id number to Main Activity
                Toast.makeText(getApplicationContext(), "City ID is : " + cityID, Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error occured " + error, Toast.LENGTH_LONG).show();

            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(arrayRequest);

        //returned null problem
        return cityID;
    }


    //This method only witth get the city ID and call the url and save the response into weather entity. No return type
    public void paerseResponseToWeatherEntityByCityID(String cityID) {

        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityID;

        final List<WeatherReportEntity> weatherReportEntities = new ArrayList<>();

// get the json object
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray consolidatedWeatherList = response.getJSONArray("consolidated_weather");


                    for (int i =0; i< consolidatedWeatherList.length(); i++) {

                        WeatherReportEntity oneDayWeather = new WeatherReportEntity();
                        JSONObject firstDayFromApi = (JSONObject) consolidatedWeatherList.get(i);

                        oneDayWeather.setId(firstDayFromApi.getInt("id"));
                        oneDayWeather.setWeatherStateName(firstDayFromApi.getString("weather_state_name"));
                        oneDayWeather.setWeatherStateAbbr(firstDayFromApi.getString("weather_state_abbr"));
                        oneDayWeather.setWindDirectionCompass(firstDayFromApi.getString("wind_direction_compass"));
                        oneDayWeather.setCreated(firstDayFromApi.getString("created"));
                        oneDayWeather.setApplicableDate(firstDayFromApi.getString("applicable_date"));
                        oneDayWeather.setMinTemp(firstDayFromApi.getLong("min_temp"));
                        oneDayWeather.setMaxTemp(firstDayFromApi.getLong("max_temp"));
                        oneDayWeather.setTheTemp(firstDayFromApi.getLong("the_temp"));
                        oneDayWeather.setWindSpeed(firstDayFromApi.getLong("wind_speed"));
                        oneDayWeather.setWindDirection(firstDayFromApi.getLong("wind_direction"));
                        oneDayWeather.setAirPressure(firstDayFromApi.getLong("air_pressure"));
                        oneDayWeather.setHumidity(firstDayFromApi.getInt("humidity"));
                        oneDayWeather.setVisibility(firstDayFromApi.getLong("visibility"));
                        oneDayWeather.setPredictability(firstDayFromApi.getInt("predictability"));
                        weatherReportEntities.add(oneDayWeather);
                    }
                    //  forecastByIDResponse.onResponse(weatherReportEntities);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            // get the property called "consolidated_weather" which is an array

            // get each item in the array and assign it to a new WeatherReportEntity object.

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(objectRequest);

    }

    //This method will get the city name, call the url and save the reponse into weather entity
    public void  paerseResponseToWeatherEntityByCityName(String cityName){
        String cityID = getCityIdByityName(cityName);
        paerseResponseToWeatherEntityByCityID(cityID);
    }




}
