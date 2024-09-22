package weather_server;

import api.ApiWeatherService;
import api.ApiWeatherServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;

public class WeatherTCPServer {
    
    public static void main(String[] args) {
        System.out.println("Nhập tên thành phố");
        Scanner sc= new Scanner(System.in);
        String namecity = sc.nextLine();
        
        ApiWeatherService i = new ApiWeatherServiceImpl();
        Weather weather = i.getWeatherByCityName(namecity);
        
        if (weather != null) {
            System.out.println("Tên khu vực: " + weather.getLocation().getName());
            System.out.println("Quốc gia: " + weather.getLocation().getCountry());
            System.out.println("Vĩ độ: " + weather.getLocation().getLat());
            System.out.println("Kinh độ: " + weather.getLocation().getLon());
            System.out.println("Nhiệt độ: " + weather.getCurrent().getTemp_c() + "°C");
            System.out.println("Hướng gió: " + weather.getCurrent().getWind_dir());
            System.out.println("Icon: " + weather.getCurrent().getIcon());
        }else {
            System.out.println("Không thể lấy dữ liệu thời tiết");
        }
            
        
    }
}
