package app.dto;

import app.entity.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class AuthorDTO {

    @JsonProperty("author_id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    // ___________________________________________________________________

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.lastName = author.getLastName();
        this.birthDate = author.getBirthDate();
    }

}