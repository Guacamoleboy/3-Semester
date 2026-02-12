package app.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "person")
public class Person {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(mappedBy="person", cascade = CascadeType.ALL)
    @Column(name = "person_detail")
    private PersonDetail personDetail;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    @Column(name = "fees")
    private Set<Fee> fees = new HashSet<>();

    // ____________________________________________________________

    public void addPersonDetail(PersonDetail personDetail) {
        this.personDetail = personDetail;
        if (personDetail != null) {
            personDetail.setPerson(this);
        }
    }

    // ____________________________________________________________

    public void addFee(Fee fee) {
        this.fees.add(fee);
        if (fee != null) {
            fee.setPerson(this);
        }
    }

}