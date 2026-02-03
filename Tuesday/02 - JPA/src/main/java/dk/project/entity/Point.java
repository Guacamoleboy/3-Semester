package dk.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Point {

    // Attributes
    @Id
    @GeneratedValue
    private int id;
    private int x;
    private int y;

    // _________________________________________________________

    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }

}