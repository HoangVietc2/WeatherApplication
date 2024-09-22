/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author OS
 */
public class Weather {
    private Location location;
    private Current current;

    public Weather(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    // Getters and Setters
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
    @Override
	public String toString() {
		return "City: " + this.location.getName() + "," 
                                + "Temperature: " + this.location.getCountry() + ","
				+ "Temperature: " + this.current.getTemp_c() + " C," 
				+ "Wind Speed: " + this.current.getWind_dir() + " Mph,"
				;
	}
}



