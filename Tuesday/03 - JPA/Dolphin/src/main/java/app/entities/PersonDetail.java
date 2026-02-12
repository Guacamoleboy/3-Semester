package app.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PersonDetail {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "adress", nullable = false)
    private String Address;

    @Column(name = "zip", nullable = false)
    private int zip;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToOne
    @MapsId
    @ToString.Exclude
    @Column(name = "person", nullable = false, unique = true)
    private Person person;

    // ____________________________________________________________

    public PersonDetail(String address, int zip, String city, int age) {
        Address = address;
        this.zip = zip;
        this.city = city;
        this.age = age;
    }

}