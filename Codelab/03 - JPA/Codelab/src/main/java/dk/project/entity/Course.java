package dk.project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "course")
public class Course {

    // Bidirectional with Teacher & Student

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "course_name")
    @Enumerated(EnumType.STRING)
    private CourseName courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "start_date")
    private LocalDate startDate;

    // _______________________________________________________
    // Non-db related

    @ManyToMany(mappedBy = "courses")
    private List<Teacher> teachers;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}