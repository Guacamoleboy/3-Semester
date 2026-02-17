package app;

import app.config.PoolConfig;
import app.dto.CityInfoDTO;
import app.dto.WeatherInfoDTO;
import app.service.CityService;
import app.service.WeatherService;
import java.util.concurrent.CompletableFuture;

public class Main {

    // Attributes

    // ____________________________________________________

    public static void main(String[] args) throws Exception {

        // Initial Setup
        CityService cityService = new CityService();
        WeatherService weatherService = new WeatherService();
        String cityName = "Roskilde";

        // City Info API Call
        CompletableFuture<CityInfoDTO> cityFuture = cityService.getCityInfo(cityName);

        // Weather Info API Call
        CompletableFuture<WeatherInfoDTO> weatherFuture = cityFuture.thenCompose(city -> {
            System.out.println("By fundet: " + city.getName() +
                    " (lat=" + city.getLatitude() + ", lon=" + city.getLongitude() + ")");
            try {
                return weatherService.getWeather(city.getLatitude(), city.getLongitude());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        // Output
        weatherFuture.thenAccept(weather -> {
            System.out.println("Temperatur: " + weather.getWithUnit("temperature"));
            System.out.println("Vind: " + weather.getWithUnit("wind"));
        }).join();

        // Close pool
        PoolConfig.shutdown();

    }

}