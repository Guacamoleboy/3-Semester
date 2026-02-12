package app.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "fee")
public class Fee {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "pay_date", nullable = false)
    private LocalDate payDate;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name ="person_id", referencedColumnName = "id", nullable = false)
    private Person person;

}