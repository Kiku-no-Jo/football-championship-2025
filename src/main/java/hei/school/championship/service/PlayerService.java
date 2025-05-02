package hei.school.championship.service;

import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerCrudOperations playerCrudOperations;

    public List<Player> getAllPlayers(int page, int size) {
        return playerCrudOperations.getAll(page, size);
    }

    public Player getPlayerById(String idPlayer) {
        return playerCrudOperations.findById(idPlayer);
    }

    public List<Player> saveAll(List<Player> players) {
        return playerCrudOperations.saveAll(players);
    }

    public List<Player> getPlayersByIdClub(String idClub) {
        return playerCrudOperations.findByIdClub(idClub);
    }
}
