package hei.school.championship.endpoint;

import hei.school.championship.endpoint.rest.SeasonRequest;
import hei.school.championship.entity.Season;
import hei.school.championship.service.SeasonService;
import hei.school.championship.service.exception.ClientException;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("seasons")
public class SeasonRestController {
    @Autowired
    private SeasonService seasonService;

    @GetMapping("")
    public ResponseEntity<Object> getSeasons(@RequestParam(required = false,defaultValue = "1") int page, @RequestParam(required = false,defaultValue = "5")int size){
        try {
            return ResponseEntity.ok(seasonService.getAllSeasons(page, size));
        }catch (ClientException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (ServerException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }catch (NotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseEntity<List<Season>> createSeasons(
            @RequestBody List<SeasonRequest> seasonRequests
    ) {
        List<Season> createdSeasons = seasonService.createSeasons(seasonRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeasons);
    }

    @PutMapping("/{seasonYear}/status")
    public ResponseEntity<Object> updateSeasonStatus(@PathVariable int seasonYear, @PathVariable int status){
        return null;
    }
}
