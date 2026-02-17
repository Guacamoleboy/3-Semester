package app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDTO {

    // We assume the API creator can make mistakes. Hence the object version of double.
    // LocalTime as we only want the time as exerciseDate has the date.

    // Attributes
    @JsonProperty("exercise_date")
    private LocalDate exerciseDate;                                     // Date
    @JsonProperty("exercise_type")
    private String exerciseType;                                        // Type of exercise
    @JsonProperty("time_of_day")
    private LocalTime timeOfDay;                                        // Only the time
    private Duration duration;                                          // Hours, Minutes, Seconds, Milliseconds
    private Double distance;                                            // Distance (16.75km)
    private String comment;                                             // Optional

}