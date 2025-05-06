package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.MatchMapper;
import hei.school.championship.entity.Match;
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
public class MatchCrudOperations implements CrudOperations<Match> {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MatchMapper matchMapper;

    @Override
    public List<Match> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<Match> matches = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score from match order by id asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Match match = matchMapper.apply(resultSet);
                    matches.add(match);
                }
                return matches;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Match findById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score from match where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return matchMapper.apply(resultSet);
                }
                throw new NotFoundException("Match.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @SneakyThrows
    @Override
    public List<Match> saveAll(List<Match> entities) {
        List<Match> matches = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into match (id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score) values (?, ?, ?, ?, ?, ?, ?, ?)"
                             + " on conflict (id) do nothing"
                             + " returning id, club_home_id, club_away_id, stadium, match_datetime, season_year, home_score, away_score")) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getId());
                    statement.setString(2, entityToSave.getClubPlayingHome().getId());
                    statement.setString(3, entityToSave.getClubPlayingAway().getId());
                    statement.setString(4, entityToSave.getStadium());
                    statement.setTimestamp(5, entityToSave.getMatchDateTime());
                    statement.setInt(6, entityToSave.getSeasonYear());
                    statement.setInt(7, entityToSave.getHomeScore());
                    statement.setInt(8, entityToSave.getAwayScore());
                    statement.addBatch(); // group by batch so executed as one query in database
                } catch (SQLException e) {
                    throw new ServerException(e);
                }
            });
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    matches.add(matchMapper.apply(resultSet));
                }
            }
            return matches;
        }
    }

    public List<Match> findBySeasonYear(int seasonYear) {
        List<Match> matches = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select id, club_home_id, club_away_id, stadium, match_datetime, status, season_year, home_score, away_score from match where season_year = ?")) {
            statement.setInt(1, seasonYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Match match = matchMapper.apply(resultSet);
                    matches.add(match);
                }
                return matches;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
