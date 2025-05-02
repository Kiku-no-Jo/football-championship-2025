package hei.school.championship.endpoint;

import hei.school.championship.service.PlayerService;
import hei.school.championship.service.exception.ClientException;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("players")
public class PlayerRestController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("")
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
}
