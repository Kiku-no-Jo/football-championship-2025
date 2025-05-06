package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.MatchScorer;
import hei.school.championship.entity.Player;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class MatchScorerMapper implements Function<ResultSet, MatchScorer> {
    private final PlayerCrudOperations playerCrudOperations;
    private final ClubCrudOperations clubCrudOperations;

    @SneakyThrows
    @Override
    public MatchScorer apply(ResultSet resultSet) {
        Player player = playerCrudOperations.findById(resultSet.getString("player_id"));
        Club club = clubCrudOperations.findById(resultSet.getString("club_id"));

        MatchScorer matchScorer = new MatchScorer();
        matchScorer.setId(resultSet.getString("id"));
        matchScorer.setMatchId(resultSet.getString("match_id"));
        matchScorer.setClub(club);
        matchScorer.setPlayer(player);
        matchScorer.setGoalTime(resultSet.getInt("goal_time"));
        matchScorer.setOwnGoal(resultSet.getBoolean("is_own_goal"));
        return matchScorer;
    }
}
