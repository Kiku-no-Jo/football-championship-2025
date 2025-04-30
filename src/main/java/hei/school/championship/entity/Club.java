package hei.school.championship.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Club {
    private String id;
    private String name;
    private String acronym;
    private int yearCration;
    private String stadium;
    private Coach coach;
}
