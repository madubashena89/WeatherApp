package top.stores.weatherapp;

import android.content.Context;
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

import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherDataSeervice {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";


    Context context;

    String cityID;


    public WeatherDataSeervice(Context context) {
        this.context = context;
    }

    //call back
    public interface VolleyResponseListner {
        void onError(String message);

        void onResponse(String cityID);


    }


    public void getCityID(String cityName, final VolleyResponseListner volleyResponseListner) {

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
                //Toast.makeText(context, "City ID is : " + cityID, Toast.LENGTH_SHORT).show();
                volleyResponseListner.onResponse(cityID);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error occured " + error, Toast.LENGTH_LONG).show();
                volleyResponseListner.onError("Something went wrong..");

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(arrayRequest);

        //returned null problem
        //return cityID;
    }


    public interface ForecastByIDResponse {
        void onError(String message);

        void onResponse(List<WeatherReportEntity> weatherReportEntities) throws ExecutionException, InterruptedException;


    }

    public void getCityForecastByID(String cityID, final ForecastByIDResponse forecastByIDResponse) {

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
                       forecastByIDResponse.onResponse(weatherReportEntities);


                } catch (JSONException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }


            // get the property called "consolidated_weather" which is an array

            // get each item in the array and assign it to a new WeatherReportEntity object.

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                forecastByIDResponse.onError("Oops something went wrong..");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);

    }

    public interface  GetCityByForecastByNameCallBack{
        void onError(String message);
        void onResponse(List<WeatherReportEntity> weatherReportEntities);
    }



    public void  getCityForecastByName(String cityName, final GetCityByForecastByNameCallBack getCityByForecastByNameCallBack){
       // List<WeatherReportEntity>

        getCityID(cityName, new VolleyResponseListner() {
            @Override
            public void onError(String message) {
                Toast.makeText(context, "Can not query the city ID..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cityID) {

                getCityForecastByID(cityID, new ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        getCityByForecastByNameCallBack.onError("Can not show the weather data now..");
                    }

                    @Override
                    public void onResponse(List<WeatherReportEntity> weatherReportEntities) {
                        getCityByForecastByNameCallBack.onResponse(weatherReportEntities);
                    }
                });

            }
        });

    }





}