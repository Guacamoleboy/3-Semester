package app.dto;

import app.entities.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeDTO {

    // Attributes
    @JsonProperty("fee_id")
    private Integer id;

    @JsonProperty("fee.amount")
    private int amount;

    @JsonProperty("fee_pay_date")
    private LocalDate payDate;

    @JsonProperty("person_id")
    private Person person;

}