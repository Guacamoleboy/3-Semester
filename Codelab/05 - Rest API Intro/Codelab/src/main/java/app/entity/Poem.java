package app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poems")
public class Poem {

    // _________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //              PgAdmin
    //              _______
    //              id | title | content | author | release_date | last_updated
    //
    // __________________
    // Tested: NO
    // By: N/A

    // _________________________________________________________________________

    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "title", nullable = false, unique = true)
    private String title;
    @Column(name = "content", nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @Column(name = "release_date")
    private LocalDate release;
    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    // ___________________________________________________________________________
    // Each time

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = LocalDate.now();
    }

    // ___________________________________________________________________________
    // First persist

    @PrePersist
    public void prePersist() {
        this.lastUpdated = LocalDate.now();
    }

}