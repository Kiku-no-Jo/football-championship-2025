package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.entity.TradeHistory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class TradeMapper implements Function<ResultSet, TradeHistory> {
    private final ClubCrudOperations clubCrudOperations;
    private final PlayerCrudOperations playerCrudOperations;

    @SneakyThrows
    @Override
    public TradeHistory apply(ResultSet resultSet) {
        TradeHistory tradeHistory = new TradeHistory();
        tradeHistory.setId(resultSet.getString("id"));
        tradeHistory.setPlayer(playerCrudOperations.findSimplePlayerById(resultSet.getString("player_id")));

        String fromClubId = resultSet.getString("from_club_id");
        if (!resultSet.wasNull()) {
            tradeHistory.setFromClub(clubCrudOperations.findById(fromClubId));
        }

        tradeHistory.setToClub(clubCrudOperations.findById(resultSet.getString("to_club_id")));
        tradeHistory.setTransferDate(resultSet.getTimestamp("transfer_date"));

        return tradeHistory;
    }
}
