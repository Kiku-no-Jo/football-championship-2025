package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Club {
    private String id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private Coach coach;
}
