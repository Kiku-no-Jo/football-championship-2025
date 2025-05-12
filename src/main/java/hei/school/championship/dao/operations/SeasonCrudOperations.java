package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.SeasonMapper;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.Season;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeasonCrudOperations implements CrudOperations<Season> {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SeasonMapper seasonMapper;

    @Override
    public List<Season> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<Season> seasons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, year, alias, status from season order by year asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Season season = seasonMapper.apply(resultSet);
                    seasons.add(season);
                }
                return seasons;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Season findById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, year, alias, status from season where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return seasonMapper.apply(resultSet);
                }
                throw new NotFoundException("Season.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @SneakyThrows
    @Override
    public List<Season> saveAll(List<Season> entities) {
        List<Season> seasons = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into season (id, year, alias, status) values (?, ?, ?, CAST(? AS season_status))"
                             + " on conflict (id) do nothing"
                             + " returning id, year, alias, status");) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getId());
                    statement.setInt(2, entityToSave.getYear());
                    statement.setString(3, entityToSave.getAlias());
                    statement.setString(4, entityToSave.getStatus().name());
                    statement.addBatch(); // group by batch so executed as one query in database
                } catch (SQLException e) {
                    throw new ServerException(e);
                }
            });
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    seasons.add(seasonMapper.apply(resultSet));
                }
            }
            return seasons;
        }
    }

    public int findLatestSeasonIdNumber() {
        String sql = "SELECT id FROM season ORDER BY id DESC LIMIT 1";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                String latestId = resultSet.getString("id");
                // Extract the numeric part (e.g., "S04" → 4)
                return Integer.parseInt(latestId.substring(1));
            }
            return 0; // No seasons exist yet
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public Season findBySeasonYear(int seasonYear) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, year, alias, status from season where year = ?")) {
            statement.setInt(1, seasonYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return seasonMapper.apply(resultSet);
                }
                throw new NotFoundException("Season.id=" + seasonYear + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }


}
