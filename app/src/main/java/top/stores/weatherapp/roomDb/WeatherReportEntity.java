package top.stores.weatherapp.roomDb;

import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_table")
public class WeatherReportEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "weather_state_name")
    private String weatherStateName;

    @ColumnInfo(name = "weather_state_abbr")
    private String weatherStateAbbr;

    @ColumnInfo(name = "wind_direction_compass")
    private String windDirectionCompass;

    @ColumnInfo(name = "created")
    private String created;

    @ColumnInfo(name = "applicable_date")
    private String applicableDate;

    @ColumnInfo(name = "min_temp")
    private float  minTemp;

    @ColumnInfo(name = "max_temp")
    private float  maxTemp;

    @ColumnInfo(name = "the_temp")
    private float  theTemp;

    @ColumnInfo(name = "wind_speed")
    private float windSpeed;

    @ColumnInfo(name = "wind_direction")
    private float windDirection;

    @ColumnInfo(name = "air_pressure")
    private float airPressure;

    @ColumnInfo(name = "humidity")
    private int humidity;

    @ColumnInfo(name = "visibility")
    private float visibility;

    @ColumnInfo(name = "predictability")
    private int predictability;

    public WeatherReportEntity(int id, String weatherStateName, String weatherStateAbbr, String windDirectionCompass, String created, String applicableDate, float minTemp, float maxTemp, float theTemp, float windSpeed, float windDirection, float airPressure, int humidity, float visibility, int predictability) {
        this.id = id;
        this.weatherStateName = weatherStateName;
        this.weatherStateAbbr = weatherStateAbbr;
        this.windDirectionCompass = windDirectionCompass;
        this.created = created;
        this.applicableDate = applicableDate;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.theTemp = theTemp;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.airPressure = airPressure;
        this.humidity = humidity;
        this.visibility = visibility;
        this.predictability = predictability;
    }

    public WeatherReportEntity() {
    }

    @Override
    public String toString() {
        return  " Weather State :  " + weatherStateName + '\n' +
                " Date : " + applicableDate +  '\n' +
                " Low Temp : " + minTemp + '\n' +
                " High Temp : " + maxTemp + '\n' +
                " Temp : " + theTemp + '\n' +
                " Wind Speed : " + windSpeed + '\n' +
                " Air Pressure : " + airPressure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public String getWeatherStateAbbr() {
        return weatherStateAbbr;
    }

    public void setWeatherStateAbbr(String weatherStateAbbr) {
        this.weatherStateAbbr = weatherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public float getTheTemp() {
        return theTemp;
    }

    public void setTheTemp(float theTemp) {
        this.theTemp = theTemp;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public float getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(float windDirection) {
        this.windDirection = windDirection;
    }

    public float getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(float airPressure) {
        this.airPressure = airPressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public int getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }
}
