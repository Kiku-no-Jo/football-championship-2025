package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchScorer {
    private String id;
    private String matchId;
    private Club club;  // Changed from clubId to Club object
    private Player player;  // Changed from playerId to Player object
    private int goalTime;  // in minutes (1-120)
    private boolean ownGoal;

}
