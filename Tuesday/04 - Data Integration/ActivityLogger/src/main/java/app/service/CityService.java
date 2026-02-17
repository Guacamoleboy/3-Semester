package app.service;

import app.config.PoolConfig;
import app.dto.CityInfoDTO;
import app.dto.CityInfoResponseDTO;
import app.exception.ApiException;
import app.exception.ResourceNotFoundException;
import app.util.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class CityService {

    // Attributes
    private final ObjectMapper objectMapper = new ObjectMapper(); // JSON (API) -> Java Objects

    // _______________________________________________________

    public CompletableFuture<CityInfoDTO> getCityInfo(String cityName) {

        // Url + String.format of cityName (param)
        String url = String.format(
            "https://geocoding-api.open-meteo.com/v1/search?name=%s",
            cityName
        );

        // Request
        // - GET() = Method. Could be POST() too.
        // - URI.create(url) = String to URI converter
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Async request using HttpClient from PoolConfig
        // - PoolConfig.getClient() using PoolLog Threadpool
        // - sendAsync() returns a CompletableFuture<HttpResponse<String>> | Format below
        // - <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler)

        return PoolConfig.getClient()
            .sendAsync(request, BodyHandlers.ofString())

            // Only runs when a response is found
            .thenApply(response -> {

                // If we don't get 200 (OK) | Validation
                if (response.statusCode() != 200) {
                    throw new ApiException(response.statusCode(), "API returned status: " + response.statusCode());
                }

                // If success we move on to our actual call
                try {

                    // JSON to CityInfoResponseDTO (API response wrapper)
                    // public <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException
                    CityInfoResponseDTO cityInfoResponseDTO = objectMapper.readValue(response.body(), CityInfoResponseDTO.class);

                    // Validation on List<CityInfoDTO> in our wrapper
                    return ValidationUtil.requireFirst(cityInfoResponseDTO.getResults(), new ResourceNotFoundException("CityInfoDTO (CityInfoResponseDTO.results)", cityName));

                } catch (Exception e) {
                    // JSON Parse error handle
                    // - Status code 500 explicit in ApiException so not needed here
                    throw new ApiException("Failed parsing API | CityService: ", e);
                }

            });

        // return CompletableFuture<CityInfoDTO> end

    } // getCityInfo() end

}