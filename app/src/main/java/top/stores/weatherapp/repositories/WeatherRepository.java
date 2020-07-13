package top.stores.weatherapp.repositories;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

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

import top.stores.weatherapp.MySingleton;
import top.stores.weatherapp.roomDb.WeatherDao;
import top.stores.weatherapp.roomDb.WeatherDataBase;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherRepository {
    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";

    Context context;

    String cityID;

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

    //This method will retrun the city ID when ewntering the city name
    public String getCityIdByityName(String cityName) {

        String url = QUERY_FOR_CITY_ID + cityName;

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityID = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityID = cityInfo.getString("woeid");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // this worked, but it didn't return the id number to Main Activity
                Toast.makeText(context, "City ID is : " + cityID, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error occured " + error, Toast.LENGTH_LONG).show();

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);

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

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);

    }

//This method will get the city name, call the url and save the reponse into weather entity
    public void  paerseResponseToWeatherEntityByCityName(String cityName){
        String cityID = getCityIdByityName(cityName);
        paerseResponseToWeatherEntityByCityID(cityID);
    }
}
