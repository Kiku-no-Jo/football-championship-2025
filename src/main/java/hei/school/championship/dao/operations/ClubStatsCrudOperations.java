package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.ClubMapper;
import hei.school.championship.dao.mapper.ClubStatsMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.ClubStats;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClubStatsCrudOperations {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ClubStatsMapper clubStatsMapper;


    public List<ClubStats> getAllBySeasonYear( int seasonYear) {

        List<ClubStats> clubStats = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select club_id, season_year, ranking_points, scored_goals, conceded_goals, clean_sheet_number from club_statistic WHERE season_year = ?")) {
            statement.setInt(1, seasonYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ClubStats clubStat = clubStatsMapper.apply(resultSet);
                    clubStats.add(clubStat);
                }
                return clubStats;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
