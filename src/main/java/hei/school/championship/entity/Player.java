package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
    private String id;
    private String name;
    private int number;
    private PlayerPostion position;
    private String Nationality;
    private int age;
    private Club club;
}
