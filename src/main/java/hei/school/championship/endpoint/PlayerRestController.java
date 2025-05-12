package hei.school.championship.endpoint;

import hei.school.championship.endpoint.rest.PlayerRequest;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.PlayerStats;
import hei.school.championship.service.PlayerService;
import hei.school.championship.service.exception.ClientException;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.TreeMap;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<Object> getPlayers(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "5") int size,
            @RequestParam(required = false, defaultValue = "false") boolean groupByClub,
            @RequestParam(required = false) Integer ageMinimum,
            @RequestParam(required = false) Integer ageMaximum) {

        try {
            // Get all paginated players first
            List<Player> filteredPlayers = playerService.getAllPlayers(page, size).stream()

                    // Filter by age range if parameters are provided
                    .filter(player -> {
                        boolean meetsMin = (ageMinimum == null || player.getAge() >= ageMinimum);
                        boolean meetsMax = (ageMaximum == null || player.getAge() <= ageMaximum);
                        return meetsMin && meetsMax;
                    })
                    .sorted(Comparator.comparing(Player::getName))
                    .collect(Collectors.toList());

            if (groupByClub) {
                // Group the filtered players by club
                Map<String, List<Player>> playersByClub = filteredPlayers.stream()
                        .collect(Collectors.groupingBy(
                                player -> player.getClub() != null ? player.getClub().getName() : "No Club",
                                TreeMap::new,
                                Collectors.toList()
                        ));

                return ResponseEntity.ok(playersByClub);
            } else {
                return ResponseEntity.ok(filteredPlayers);
            }

        } catch (ClientException | NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ServerException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping("/players")
    public ResponseEntity<List<Player>> createOrUpdatePlayers(@RequestBody List<PlayerRequest> playerRequests) {
        List<Player> savedPlayers = playerService.createOrUpdatePlayers(playerRequests);
        return ResponseEntity.ok(savedPlayers);
    }

    @GetMapping("/players/{idPlayer}/statistics/{seasonYear}")
    public ResponseEntity<Object> getPlayerStatistics(@PathVariable String idPlayer, @PathVariable int seasonYear) {
        return ResponseEntity.ok(playerService.getPlayerStats(idPlayer, seasonYear));
    }
}
