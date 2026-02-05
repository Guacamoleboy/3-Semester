package dk.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private Double salary;

    @Column(nullable = false, length = 50)
    private String department;

}