package app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {

    // Attributes
    private boolean adult;
    private Integer id;
    private List<GenreDTO> genres;
    @JsonProperty("imdb_id")
    private String imdbId;
    @JsonProperty("origin_country")
    private List<String> originCountry;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("original_title")
    private String originalTitle;
    private String overview;
    private Double popularity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("release_date")
    private LocalDate releaseDate;
    private Integer runtime;
    private String status;
    private String title;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;

    // ___________________________________________

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GenreDTO {
        private Integer id;
        private String name;
    }

    // ___________________________________________

    public String getReleaseYear() {
        if (releaseDate != null) {
            return String.valueOf(releaseDate.getYear());
        }
        return "N/A";
    }

}