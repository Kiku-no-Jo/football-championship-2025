package hei.school.championship.dao.mapper;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.CoachCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.PlayerPostion;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlayerMapper implements Function<ResultSet, Player> {
    private final ClubCrudOperations clubCrudOperations;

    @SneakyThrows
    @Override
    public Player apply(ResultSet resultSet) {
        String idClub = resultSet.getString("club_id");
        Club club = clubCrudOperations.findById(idClub);

        Player player = new Player();
        player.setId(resultSet.getString("id"));
        player.setName(resultSet.getString("name"));
        player.setNumber(resultSet.getInt("number"));
        player.setPosition(PlayerPostion.valueOf(resultSet.getString("position")));
        player.setAge(resultSet.getInt("age"));
        player.setClub(club);
        return null;
    }
}
