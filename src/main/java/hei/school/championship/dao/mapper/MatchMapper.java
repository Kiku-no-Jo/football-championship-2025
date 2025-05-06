package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Match;
import hei.school.championship.entity.MatchStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MatchMapper implements Function<ResultSet, Match> {
    private final ClubCrudOperations clubCrudOperations;
    @SneakyThrows
    @Override
    public Match apply(ResultSet resultSet) {
        String clubHomeId = resultSet.getString("club_home_id");
        String clubAwayId = resultSet.getString("club_away_id");
        Club clubHome = clubCrudOperations.findById(clubHomeId);
        Club clubAway = clubCrudOperations.findById(clubAwayId);

        Match match = new Match();
        match.setId(resultSet.getString("id"));
        match.setClubPlayingHome(clubHome);
        match.setClubPlayingAway(clubAway);
        match.setStadium(resultSet.getString("stadium"));
        match.setMatchDateTime(resultSet.getTimestamp("match_datetime"));
        match.setActualStatus(MatchStatus.valueOf(resultSet.getString("status")));
        match.setSeasonYear(resultSet.getInt("season_year"));
        match.setHomeScore(resultSet.getInt("home_score"));
        match.setAwayScore(resultSet.getInt("away_score"));
        return match;
    }
}
