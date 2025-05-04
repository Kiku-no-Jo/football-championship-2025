package hei.school.championship.endpoint.rest;

import hei.school.championship.entity.PlayerPosition;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Position;

@Getter
@Setter
public class PlayerRequest {
    private String id;
    private String name;
    private int number;
    private PlayerPosition position;  // Assuming Position is an enum
    private String nationality;
    private int age;

    // Getters and setters
    // (Or use Lombok @Data if preferred)
}
