package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.MatchScorerMapper;
import hei.school.championship.dao.mapper.PlayerMapper;
import hei.school.championship.entity.Match;
import hei.school.championship.entity.MatchScorer;
import hei.school.championship.entity.Player;
import hei.school.championship.service.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MatchScorerCrudOperations {
    private final DataSource dataSource;
    private final MatchScorerMapper matchScorerMapper;

    public List<MatchScorer> findByMatchAndClub(String matchId, String clubId) {
        List<MatchScorer> scorers = new ArrayList<>();
        String sql = "SELECT id, match_id, club_id, player_id, goal_time, is_own_goal, is_penalty " +
                "FROM match_scorers WHERE match_id = ? AND club_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, matchId);
            statement.setString(2, clubId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MatchScorer scorer = matchScorerMapper.apply(resultSet);
                    scorers.add(scorer);
                }
                return scorers;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public List<MatchScorer> findByMatchId(String matchId) {
        List<MatchScorer> scorers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select id, match_id, club_id, player_id, goal_time, is_own_goal from match_scorers where match_id = ?")) {
            statement.setString(1, matchId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MatchScorer scorer = matchScorerMapper.apply(resultSet);
                    scorers.add(scorer);
                }
                return scorers;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public List<MatchScorer> findByPlayerId(String playerId) {
        List<MatchScorer> scorers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement("select id, match_id, club_id, player_id, goal_time, is_own_goal from match_scorers where player_id = ?")) {
            statement.setString(1, playerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MatchScorer scorer = matchScorerMapper.apply(resultSet);
                    scorers.add(scorer);
                }
                return scorers;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public List<String> findScoringMatchIdsByPlayerIdAndSeason(String playerId, int seasonYear) {
        List<String> matchIds = new ArrayList<>();
        String sql = """
        SELECT DISTINCT ms.match_id
        FROM match_scorers ms
        JOIN match m ON ms.match_id = m.id
        WHERE ms.player_id = ?
          AND m.season_year = ?
    """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerId);
            statement.setInt(2, seasonYear);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    matchIds.add(rs.getString("match_id"));
                }
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }

        return matchIds;
    }

    public int countGoalsByPlayerIdAndSeason(String playerId, int seasonYear) {
        String sql = """
        SELECT COUNT(*) AS goal_count
        FROM match_scorers ms
        JOIN match m ON ms.match_id = m.id
        WHERE ms.player_id = ?
          AND m.season_year = ?
    """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerId);
            statement.setInt(2, seasonYear);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) return rs.getInt("goal_count");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }

        return 0;
    }

}

