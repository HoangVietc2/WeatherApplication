/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author OS
 */
public class Location {
    private String name;
    private String country ;

    public Location(String name, String country, double lat, double lon, int localtime_epoch, String localtime) {
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.localtime_epoch = localtime_epoch;
        this.localtime = localtime;
    }
    // Thêm trường region nếu cần
    private double lat; // Thêm trường lat
    private double lon; // Thêm trường lon
    private int localtime_epoch;
    private String localtime ;

    public int getLocaltime_epoch() {
        return localtime_epoch;
    }

    public void setLocaltime_epoch(int localtime_epoch) {
        this.localtime_epoch = localtime_epoch;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }
    

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

 
    public double getLat() {
        return lat; // Thêm getter cho lat
    }

    public void setLat(double lat) {
        this.lat = lat; // Thêm setter cho lat
    }

    public double getLon() {
        return lon; // Thêm getter cho lon
    }

    public void setLon(double lon) {
        this.lon = lon; // Thêm setter cho lon
    }

}
