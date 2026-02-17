package app.service;

import app.config.PoolConfig;
import app.dto.WeatherInfoDTO;
import app.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class WeatherService {

    // ###################################################### //
    // See CityService for explanation of each step in depth. //
    // ###################################################### //

    // Attributes
    private final ObjectMapper objectMapper = new ObjectMapper();

    // _______________________________________________________

    public CompletableFuture<WeatherInfoDTO> getWeather(double latitude, double longitude) {

        // Url setup
        String url = String.format(
                // Important or it'll use the machines system language (dk -> 55,00 -> error as it needs "." instead of ",")
                Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,wind_speed_10m",
                latitude,
                longitude
        );

        // Request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Executing of our request and return in CompletableFuture<T> format
        return PoolConfig.getClient()
            .sendAsync(request, BodyHandlers.ofString())
            .thenApply(response -> {

                // Validation
                if (response.statusCode() != 200) {
                    throw new ApiException("Weather API status: " + response.statusCode());
                }

                // The call
                try {

                    // public <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException
                    WeatherInfoDTO weatherInfoDTO = objectMapper.readValue(response.body(), WeatherInfoDTO.class);

                    // Validation
                    if (weatherInfoDTO == null || weatherInfoDTO.getCurrent() == null || weatherInfoDTO.getCurrentUnits() == null) {
                        throw new ApiException("Weather API incomplete data");
                    }

                    return weatherInfoDTO;
                } catch (Exception e) {
                    throw new ApiException("Failed parsing API | CityService: ", e);
                }

            });

        // return CompletableFuture<WeatherInfoDTO> end

    } // getWeather() end

}