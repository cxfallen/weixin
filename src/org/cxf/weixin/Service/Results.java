package org.cxf.weixin.Service;

import java.util.List;

public class Results {
	public String getCurrentCity() {
		return currentCity;
	}
	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}
	public List<WeatherData> getWeather_Data() {
		return weather_data;
	}
	public void setWeather_Data(List<WeatherData> weather_Data) {
		this.weather_data = weather_Data;
	}
	private String currentCity;
	private List<WeatherData> weather_data;
}
