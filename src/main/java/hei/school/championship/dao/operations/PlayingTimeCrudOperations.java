package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.PlayerMapper;
import hei.school.championship.dao.mapper.PlayingTimeMapper;
import hei.school.championship.entity.Player;
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
public class PlayingTimeCrudOperations {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlayingTimeMapper playingTimeMapper;

    public PlayingTime findById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, value, time_unit from playing_time where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return playingTimeMapper.apply(resultSet);
                }
                throw new NotFoundException("PlayingTime.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
