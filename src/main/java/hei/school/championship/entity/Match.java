package hei.school.championship.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Match {
    private String id;
    private Club clubPlayingHome;
    private Club clubPlayingAway;
    private String stadium;
    private LocalDate matchDateTime;
    private MatchStatus actualStatus;

}
