package app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {

    // _________________________________________________________________________

    // Expected Column Layout in DB
    // __________________
    //
    //              PgAdmin
    //              _______
    //              id | name | last_name | birth_date
    //
    // __________________
    // Tested: NO
    // By: N/A

    // _________________________________________________________________________

    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    // _________________________________________________________________________
    // Relations

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Poem> poems;

}