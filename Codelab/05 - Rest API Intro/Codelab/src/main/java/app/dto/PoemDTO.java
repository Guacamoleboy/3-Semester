package app.dto;

import app.entity.Poem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@JsonIgnoreProperties
public class PoemDTO {

    @JsonProperty("poem_id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("author")
    private AuthorDTO author;
    @JsonProperty("release_date")
    private LocalDate release;
    @JsonProperty("last_updated")
    private LocalDate lastUpdated;

    // ___________________________________________________________________

    public PoemDTO(Poem poem) {
        this.id = poem.getId();
        this.title = poem.getTitle();
        this.content = poem.getContent();
        this.author = new AuthorDTO(poem.getAuthor());
        this.release = poem.getRelease();
        this.lastUpdated = poem.getLastUpdated();
    }

    // ___________________________________________________________________

}