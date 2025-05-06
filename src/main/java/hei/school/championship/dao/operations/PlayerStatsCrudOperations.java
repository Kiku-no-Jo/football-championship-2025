package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.PlayerStatsMapper;
import hei.school.championship.dao.mapper.PlayingTimeMapper;
import hei.school.championship.entity.PlayerStats;
import hei.school.championship.entity.PlayingTime;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PlayerStatsCrudOperations {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlayerStatsMapper playerStatsMapper;

    public PlayerStats findByPlayerId(String id, int seasonYear) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select player_id, season_year, scored_goals, playing_time_id from player_statistic where player_id = ? AND season_year = ?")) {
            statement.setString(1, id);
            statement.setInt(2, seasonYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return playerStatsMapper.apply(resultSet);
                }
                throw new NotFoundException("PlayerStats.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
