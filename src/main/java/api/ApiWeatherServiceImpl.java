/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import https.HttpHelper;
import https.HttpResponse;
import https.SimpleHttpHelper;
import java.net.URLEncoder;
import model.Current;
import model.Location;
import model.Weather;

/**
 *
 * @author OS
 */
public class ApiWeatherServiceImpl implements ApiWeatherService{

    @Override
    public Weather getWeatherByCityName(String cityName) {
        
        try {
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String api = "https://api.weatherapi.com/v1/current.json?key=9543e25dff9d4fafab034915242009&q="+encodedCityName+"&aqi=no";
            HttpHelper http = new SimpleHttpHelper();
            HttpResponse res = http.get(api);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode weatherResponse = objectMapper.readTree(res.getBody());
            
            String locationName = weatherResponse.path("location").path("name").asText();
            String country = weatherResponse.path("location").path("country").asText();
            double lat = weatherResponse.path("location").path("lat").asDouble();
            double lon = weatherResponse.path("location").path("lon").asDouble();
            int localtime_epoch = weatherResponse.path("location").path("localtime_epoch").asInt();
            String local_time = weatherResponse.path("location").path("localtime").asText();
            String wind_mph = weatherResponse.path("current").path("wind_mph").asText();
            String precip_mm = weatherResponse.path("current").path("precip_mm").asText();
            double temperature = weatherResponse.path("current").path("temp_c").asDouble();
            int humidity = weatherResponse.path("current").path("humidity").asInt();
            String icon = "https:" + weatherResponse.path("current").path("condition").path("icon").asText();
            String text = weatherResponse.path("current").path("condition").path("text").asText();
            System.out.println("TEXT" + text + precip_mm + wind_mph);
            Location location = new Location(locationName, country, lat, lon, localtime_epoch, local_time);
            Current current = new Current(temperature, wind_mph, precip_mm, humidity , icon , text);
            return new Weather(location, current);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
