package hei.school.championship.endpoint;

import hei.school.championship.endpoint.rest.PlayerRequest;
import hei.school.championship.entity.Player;
import hei.school.championship.service.PlayerService;
import hei.school.championship.service.exception.ClientException;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    public ResponseEntity<Object> getPlayers(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "5") int size) {
        try {
            return ResponseEntity.ok(playerService.getAllPlayers(page, size));
        }catch (ClientException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (ServerException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/players")
    public ResponseEntity<List<Player>> createOrUpdatePlayers(@RequestBody List<PlayerRequest> playerRequests) {
        List<Player> savedPlayers = playerService.createOrUpdatePlayers(playerRequests);
        return ResponseEntity.ok(savedPlayers);
    }

    @GetMapping("/{id}/statistics/{seasonYear}")
    public ResponseEntity<Object> getPlayerStatistics(@PathVariable int id, @PathVariable int seasonYear) {
        return null;
    }
}
