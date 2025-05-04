package hei.school.championship.service;

import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.endpoint.rest.PlayerRequest;
import hei.school.championship.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Player> createOrUpdatePlayers(List<PlayerRequest> playerRequests) {
        // Convert DTOs to entities
        List<Player> playersToSave = playerRequests.stream()
                .map(this::convertToPlayer)
                .collect(Collectors.toList());

        // Delegate to DAO
        return playerCrudOperations.saveAll(playersToSave);
    }

    private Player convertToPlayer(PlayerRequest request) {
        Player player = new Player();
        player.setId(request.getId());
        player.setName(request.getName());
        player.setNumber(request.getNumber());
        player.setPosition(request.getPosition());
        player.setNationality(request.getNationality());
        player.setAge(request.getAge());

        // Note: Handle club association if needed
        return player;
    }
}
