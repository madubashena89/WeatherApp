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

        void onResponse(WeatherReportModel weatherReportModel);


    }

    public void getCityForecastByID(String cityID, final ForecastByIDResponse forecastByIDResponse) {

        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityID;

        final List<WeatherReportModel> report = new ArrayList<>();
        // get the json object

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                   JSONArray consolidatedWeatherList = response.getJSONArray("consolidated_weather");
                   //get first item in the array
                    WeatherReportModel first_day = new WeatherReportModel();

                    JSONObject firstDayFromApi = (JSONObject) consolidatedWeatherList.get(0);

                    first_day.setId(firstDayFromApi.getInt("id"));
                    first_day.setWeatherStateName(firstDayFromApi.getString("weather_state_name"));
                    first_day.setWeatherStateName(firstDayFromApi.getString("weather_state_abbr"));
                    first_day.setWindDirectionCompass(firstDayFromApi.getString("wind_direction_compass"));
                    first_day.setCreated(firstDayFromApi.getString("created"));
                    first_day.setApplicableDate(firstDayFromApi.getString("applicable_date"));
                    first_day.setMinTemp(firstDayFromApi.getLong("min_temp"));
                    first_day.setMaxTemp(firstDayFromApi.getLong("max_temp"));
                    first_day.setTheTemp(firstDayFromApi.getLong("the_temp"));
                    first_day.setWindSpeed(firstDayFromApi.getLong("wind_speed"));
                    first_day.setWindDirection(firstDayFromApi.getLong("wind_direction"));
                    first_day.setAirPressure(firstDayFromApi.getLong("air_pressure"));
                    first_day.setHumidity(firstDayFromApi.getInt("humidity"));
                    first_day.setVisibility(firstDayFromApi.getLong("visibility"));
                    first_day.setPredictability(firstDayFromApi.getInt("predictability"));

                    forecastByIDResponse.onResponse(first_day);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            // get the property called "consolidated_weather" which is an array

            // get each item in the array and assign it to a new WeatherReportModel object.

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(objectRequest);

    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }



}