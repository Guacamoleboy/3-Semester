package app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
public class MovieWrapperDTO {

    // Using Integer as int defaults to 0. Integer defaults to null which is better for debugging
    // and finding issues collecting data from a JSON.

    // Attributes
    private Integer page;
    private List<MovieDTO> results;
    @JsonProperty("total_pages")
    private Integer totalPages;
    @JsonProperty("total_results")
    private Integer totalResults;

}