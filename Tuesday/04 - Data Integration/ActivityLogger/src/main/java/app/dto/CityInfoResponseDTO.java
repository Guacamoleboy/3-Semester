package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoResponseDTO {

    // Wrapper for CityInfoDTO as it has "results" before data
    // Response = because we receive data.
    // Sending data = just name it DTO.

    // Attributes
    private List<CityInfoDTO> results;

    @JsonProperty("generationtime_ms")
    private Double generationTimeMs;

}