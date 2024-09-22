/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

import model.Weather;

/**
 *
 * @author OS
 */
public interface ApiWeatherService {
    Weather getWeatherByCityName(String cityName);
}
