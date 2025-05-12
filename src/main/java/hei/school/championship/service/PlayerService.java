package hei.school.championship.service;

import hei.school.championship.dao.operations.MatchScorerCrudOperations;
import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.dao.operations.PlayerStatsCrudOperations;
import hei.school.championship.endpoint.rest.DurationRequest;
import hei.school.championship.endpoint.rest.PlayerRequest;
import hei.school.championship.endpoint.rest.PlayerStatsRequest;
import hei.school.championship.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerCrudOperations playerCrudOperations;
    private final PlayerStatsCrudOperations playerStatsCrudOperations;
    private final MatchScorerCrudOperations matchScorerCrudOperations;

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

    public PlayerStats getPlayerStatsByIdPLayer(String idPlayer, int seasonYear) {
        return playerStatsCrudOperations.findByPlayerId(idPlayer, seasonYear);
    }

    public List<Player> createOrUpdatePlayers(List<PlayerRequest> playerRequests) {
        // Convert DTOs to entities
        List<Player> playersToSave = playerRequests.stream()
                .map(this::convertToPlayer)
                .collect(Collectors.toList());

        // Delegate to DAO
        return playerCrudOperations.saveAll(playersToSave);
    }

    public List<Player> createOrUpdateClubPlayers(List<PlayerRequest> playerRequests, String clubId) {
        // Create club object with just the ID
        Club club = new Club();
        club.setId(clubId);

        // Convert to Players with club association
        List<Player> playersToSave = playerRequests.stream()
                .map(request -> {
                    Player player = convertToPlayer(request);
                    player.setClub(club);  // Set the club association
                    return player;
                })
                .collect(Collectors.toList());

        // Save to database (club_id will be set)
        List<Player> savedPlayers = playerCrudOperations.saveAllClubPlayer(playersToSave);

        // Return the saved Player entities (without exposing club in response if not needed)
        return savedPlayers;
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

    public PlayerStatsRequest getPlayerStats(String idPlayer, int seasonYear) {
        int goals = matchScorerCrudOperations.countGoalsByPlayerIdAndSeason(idPlayer, seasonYear);
        List<String> matchIds = matchScorerCrudOperations.findScoringMatchIdsByPlayerIdAndSeason(idPlayer, seasonYear);

        int minutesPlayed = matchIds.size() * 90;

        return new PlayerStatsRequest(
                goals,
                new DurationRequest(minutesPlayed, DurationUnit.MINUTE)
        );
    }




}
