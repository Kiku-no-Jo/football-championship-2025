package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClubStats {
    private Club club;
    private int seasonYear;
    private int rankingPoints;
    private int scoredGoals;
    private int concededGoals;
    private int cleanSheetNumber;
}
