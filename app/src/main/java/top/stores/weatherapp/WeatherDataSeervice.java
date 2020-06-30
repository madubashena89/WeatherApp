package top.stores.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherDataSeervice {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";

   Context context;

    String cityID;


    public WeatherDataSeervice(Context context) {
        this.context = context;
    }

//call back
    public interface VolleyResponseListner{
        void onError(String message);

        void onResponse(String cityID);


    }


    public void getCityID(String cityName, final VolleyResponseListner volleyResponseListner){

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


//    public List<WeatherReportModel> getCityForecastByID(String cityID){
//
//    }
//
//    public List<WeatherReportModel> getCityForecastByName(String cityName){
//
//    }





}
