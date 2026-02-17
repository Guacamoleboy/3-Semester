package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityInfoDTO {

    // Attributes
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer population;
    private List<String> postcodes;

}