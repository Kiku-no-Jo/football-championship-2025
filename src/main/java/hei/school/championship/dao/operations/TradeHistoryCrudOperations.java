package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.TradeMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.TradeHistory;
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
public class TradeHistoryCrudOperations {
    @Autowired
    DataSource dataSource;
    @Autowired
    TradeMapper tradeMapper;

    public List<TradeHistory> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<TradeHistory> tradeHistories = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, player_id, from_club_id, to_club_id, transfer_date from trade_history order by id asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TradeHistory tradeHistory = tradeMapper.apply(resultSet);
                    tradeHistories.add(tradeHistory);
                }
                return tradeHistories;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
