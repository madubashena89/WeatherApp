package top.stores.weatherapp.services;

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

import top.stores.weatherapp.MySingleton;
import top.stores.weatherapp.roomDb.WeatherReportEntity;

public class WeatherTO {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";


    Context context;

    String cityID;


    public WeatherTO(Context context) {
        this.context = context;
    }


    public String getCityID(String cityName) {

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





    public void  getCityForecastByName(String cityName){
          String cityID = getCityID(cityName);

    }





}
