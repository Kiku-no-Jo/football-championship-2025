package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.ClubMapper;
import hei.school.championship.dao.mapper.PlayerMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Player;
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

    @SneakyThrows
    @Override
    public List<Player> saveAll(List<Player> entities) {
        List<Player> players = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into player (id, name, number, position, nationality, age, club_id) values (?, ?, ?, ?, ?, ?, ?)"
                             + " on conflict (id) do update"
                             + " returning id, name, number, position, nationality, age, club_id");) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getId());
                    statement.setString(2, entityToSave.getName());
                    statement.setInt(3, entityToSave.getNumber());
                    statement.setString(4, entityToSave.getPosition().name());
                    statement.setString(5, entityToSave.getNationality());
                    statement.setInt(6, entityToSave.getAge());

                    // Handle null club
                    if (entityToSave.getClub() != null) {
                        statement.setString(7, entityToSave.getClub().getId());
                    } else {
                        statement.setNull(7, Types.VARCHAR);  // Explicitly set NULL for club_id
                    }
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
             PreparedStatement statement = connection.prepareStatement("select id, name, number, nationality, age from player where club_id = ?")) {
            statement.setString(1, idClub);
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
}
