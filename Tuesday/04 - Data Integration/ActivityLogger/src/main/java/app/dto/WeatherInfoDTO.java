package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfoDTO {

    // Attributes
    private String timezone;
    private CurrentDTO current;
    @JsonProperty("current_units")
    private CurrentUnitsDTO currentUnits;

    // ________________________________________________________________
    // Nested class for "current"

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class CurrentDTO {
        @JsonProperty("temperature_2m")
        private Double temperature;
        @JsonProperty("wind_speed_10m")
        private Double windSpeed;
    }

    // ________________________________________________________________
    // Nested class for "current_units"

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class CurrentUnitsDTO {
        @JsonProperty("temperature_2m")
        private String temperatureUnit;
        @JsonProperty("wind_speed_10m")
        private String windSpeedUnit;
    }

    // ________________________________________________________________

    public String getWithUnit(String searchType) {

        // Initial
        if (current == null || currentUnits == null) return null;

        // Switch-case over query
        switch (searchType.toLowerCase()) {
            case "temp":
            case "temperature":
                if (current.getTemperature() != null && currentUnits.getTemperatureUnit() != null) {
                    return current.getTemperature() + currentUnits.getTemperatureUnit();
                }
                break;
            case "wind":
            case "windspeed":
            case "wind_speed":
                if (current.getWindSpeed() != null && currentUnits.getWindSpeedUnit() != null) {
                    return current.getWindSpeed() + " " + currentUnits.getWindSpeedUnit();
                }
                break;
            default:
                throw new IllegalArgumentException("Ikke godkendt query format: " + searchType);
        }
        return null;
    }

}