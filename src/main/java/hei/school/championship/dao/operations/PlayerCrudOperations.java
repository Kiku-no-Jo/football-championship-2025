package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.ClubMapper;
import hei.school.championship.dao.mapper.PlayerMapper;
import hei.school.championship.dao.mapper.PlayerSimpleMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Player;
import hei.school.championship.service.PlayerService;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerCrudOperations implements CrudOperations<Player> {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlayerMapper playerMapper;
    @Autowired
    private PlayerSimpleMapper playerSimpleMapper;

    @Override
    public List<Player> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<Player> players = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, number, position, nationality, age, club_id from player order by id asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Player player = playerMapper.apply(resultSet);
                    players.add(player);
                }
                return players;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Player findById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, number, position, nationality, age, club_id from player where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return playerMapper.apply(resultSet);
                }
                throw new NotFoundException("Player.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public Player findSimplePlayerById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, number, position, nationality, age from player where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return playerSimpleMapper.apply(resultSet);
                }
                throw new NotFoundException("Player.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }



    @SneakyThrows
    @Override
    public List<Player> saveAll(List<Player> entities) {
        List<Player> players = new ArrayList<>();
        String sql = "INSERT INTO player (id, name, number, position, nationality, age) " +
                "VALUES (?, ?, ?, CAST(? AS player_position), ?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET " +
                "name = EXCLUDED.name, " +
                "number = EXCLUDED.number, " +
                "position = EXCLUDED.position, " +
                "nationality = EXCLUDED.nationality, " +
                "age = EXCLUDED.age " +
                "RETURNING id, name, number, position, nationality, age";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Player entityToSave : entities) {
                // Set all 7 parameters
                statement.setString(1, entityToSave.getId());
                statement.setString(2, entityToSave.getName());
                statement.setInt(3, entityToSave.getNumber());
                statement.setString(4, entityToSave.getPosition().name());
                statement.setString(5, entityToSave.getNationality());
                statement.setInt(6, entityToSave.getAge());

                statement.addBatch();
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    players.add(playerSimpleMapper.apply(resultSet));
                }
            }
            return players;
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @SneakyThrows
    public List<Player> saveAllClubPlayer(List<Player> entities) {
        List<Player> players = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into player (id, name, number, position, nationality, age, club_id) values (?, ?, ?, CAST(? AS player_position), ?, ?, ?)"
                             + " on conflict (id) do nothing"
                             + " returning id, name, number, position, nationality, age, club_id");) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getId());
                    statement.setString(2, entityToSave.getName());
                    statement.setInt(3, entityToSave.getNumber());
                    statement.setString(4, entityToSave.getPosition().name());
                    statement.setString(5, entityToSave.getNationality());
                    statement.setInt(6, entityToSave.getAge());
                    statement.setString(7, entityToSave.getClub().getId());
                    statement.addBatch(); // group by batch so executed as one query in database
                } catch (SQLException e) {
                    throw new ServerException(e);
                }
            });
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    players.add(playerMapper.apply(resultSet));
                }
            }
            return players;
        }
    }

    public List<Player> findByIdClub(String idClub){
        List<Player> players = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select id, name, number, position, nationality, age from player where club_id = ?")) {
            statement.setString(1, idClub);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Player player = playerSimpleMapper.apply(resultSet);
                    players.add(player);
                }
                return players;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
