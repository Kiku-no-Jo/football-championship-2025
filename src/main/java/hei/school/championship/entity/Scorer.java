package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scorer {
    private PlayerMinimalInfo player;
    private int minuteOfGoal;
    private boolean ownGoal;
}
