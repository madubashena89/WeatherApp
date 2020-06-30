package top.stores.weatherapp;

public class WeatherReportModel {

    private int id;
    private String weatherStateName;
    private String weatherStateAbbr;
    private String windDirectionCompass;
    private String created;
    private String applicableDate;
    private float  minTemp;
    private float  maxTemp;
    private float  theTemp;
    private float windSpeed;
    private float windDirection;
    private float airPressure;
    private int humidity;
    private float visibility;
    private int predictability;

    public WeatherReportModel(int id, String weatherStateName, String weatherStateAbbr, String windDirectionCompass, String created, String applicableDate, float minTemp, float maxTemp, float theTemp, float windSpeed, float windDirection, float airPressure, int humidity, float visibility, int predictability) {
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

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "id=" + id +
                ", weatherStateName='" + weatherStateName + '\'' +
                ", weatherStateAbbr='" + weatherStateAbbr + '\'' +
                ", windDirectionCompass='" + windDirectionCompass + '\'' +
                ", created='" + created + '\'' +
                ", applicableDate='" + applicableDate + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", theTemp=" + theTemp +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                ", airPressure=" + airPressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", predictability=" + predictability +
                '}';
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
