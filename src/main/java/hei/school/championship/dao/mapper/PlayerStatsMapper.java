package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.dao.operations.PlayingTimeCrudOperations;
import hei.school.championship.entity.PlayerStats;
import hei.school.championship.entity.PlayingTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlayerStatsMapper implements Function<ResultSet, PlayerStats> {
    private final PlayingTimeCrudOperations playingTimeCrudOperations;

    @SneakyThrows
    @Override
    public PlayerStats apply(ResultSet resultSet) {
        PlayingTime playingTime = playingTimeCrudOperations.findById(resultSet.getString("playing_time_id"));

        PlayerStats playerStats = new PlayerStats();
        playerStats.setScoredGoals(resultSet.getInt("scored_goals"));
        playerStats.setPlayingTime(playingTime);

        return  playerStats;
    }
}
