package hei.school.championship.endpoint;

import hei.school.championship.endpoint.mapper.ClubRestMapper;
import hei.school.championship.endpoint.mapper.CoachRestMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Coach;
import hei.school.championship.service.ClubService;
import hei.school.championship.service.CoachService;
import hei.school.championship.service.PlayerService;
import hei.school.championship.service.exception.ClientException;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
public class ClubRestController {
    private final CoachService coachService;
    private final ClubService clubService;
    private final PlayerService playerService;
    private final CoachRestMapper coachRestMapper;
    private final ClubRestMapper clubRestMapper;

    @GetMapping("/clubs")
    public ResponseEntity<Object> getClubs(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "5") int size){
        try {
            return ResponseEntity.ok(clubService.getAllClubs(page, size));
        }catch (ClientException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (ServerException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/clubs")
    public ResponseEntity<Object> updateClub(@RequestBody Club club) {
        return null ;
    }

    @GetMapping("/clubs/{id}/players")
    public ResponseEntity<Object> getClubPlayers(@RequestParam(required = true) @PathVariable String id) {
        try {
            return ResponseEntity.ok(playerService.getPlayersByIdClub(id));
        }catch (ClientException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (ServerException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/clubs/{id}/players")
    public ResponseEntity<Object> updateClubPlayers(@RequestBody Club club, @PathVariable String id) {
        return null ;
    }

    @PostMapping("/clubs/{id}/players")
    public ResponseEntity<Object> addClubPlayers(@RequestBody List<Club> clubs, @PathVariable String id) {
        try {
            return ResponseEntity.ok(clubService.saveAll(clubs));
        }catch (ClientException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (ServerException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/clubs/statistics/{seasonYear}")
    public ResponseEntity<Object> getClubStatistics(@PathVariable String seasonYear) {
        return null ;
    }
}
