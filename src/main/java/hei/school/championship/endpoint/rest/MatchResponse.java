package hei.school.championship.endpoint.rest;

import hei.school.championship.entity.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponse {
    private String id;
    private MatchClub clubPlayingHome;
    private MatchClub clubPlayingAway;
    private String stadium;
    private Timestamp matchDatetime;
    private MatchStatus actualStatus;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchClub {
        private String id;
        private String name;
        private String acronym;
        private int score;
        private List<Scorer> scorers;

        // getters/setters
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Scorer {
        private PlayerMinimalInfo player;
        private int minuteOfGoal;
        private boolean ownGoal;

        // getters/setters
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerMinimalInfo {
        private String id;
        private String name;
        private int number;

    }
}
