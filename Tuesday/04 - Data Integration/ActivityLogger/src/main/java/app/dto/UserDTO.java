package app.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    // Attributes
    private String name;
    private int age;
    private double weight;

}