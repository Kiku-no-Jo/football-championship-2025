package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Match {
    private String id;
    private Club clubPlayingHome;
    private Club clubPlayingAway;
    private String stadium;
    private Timestamp matchDateTime;
    private MatchStatus actualStatus;
    private int homeScore;
    private int awayScore;
    private int seasonYear;

}
