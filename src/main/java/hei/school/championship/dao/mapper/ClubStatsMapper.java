package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.CoachCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.ClubStats;
import hei.school.championship.entity.Coach;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClubStatsMapper implements Function<ResultSet, ClubStats> {
    private final ClubCrudOperations clubCrudOperations;

    @SneakyThrows
    @Override
    public ClubStats apply(ResultSet resultSet) {
        Club club = clubCrudOperations.findById(resultSet.getString("club_id"));

        ClubStats clubStats = new ClubStats();
        clubStats.setClub(club);
        clubStats.setSeasonYear(resultSet.getInt("season_year"));
        clubStats.setRankingPoints(resultSet.getInt("ranking_points"));
        clubStats.setScoredGoals(resultSet.getInt("scored_goals"));
        clubStats.setConcededGoals(resultSet.getInt("conceded_goals"));
        clubStats.setCleanSheetNumber(resultSet.getInt("clean_sheet_number"));
        return clubStats;
    }
}
