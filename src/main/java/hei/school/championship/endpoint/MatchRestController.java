package hei.school.championship.endpoint;


import hei.school.championship.dao.mapper.MatchScorerMapper;
import hei.school.championship.endpoint.mapper.MatchRestMapper;
import hei.school.championship.endpoint.rest.MatchResponse;
import hei.school.championship.entity.Match;
import hei.school.championship.entity.MatchScorer;
import hei.school.championship.service.MatchScorerService;
import hei.school.championship.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MatchRestController {
    private final MatchService matchService;
    private final MatchRestMapper matchMapper;
    private final MatchScorerService scorerService;

    @GetMapping("/matches/{seasonYear}")
    public ResponseEntity<Object> getMatches(@PathVariable int seasonYear){
        List<Match> matches = matchService.findBySeasonYear(seasonYear);
        List<MatchResponse> response = matches.stream()
                .map(matchMapper::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/scorers/{matchId}")
    public List<MatchScorer> getScorers(@PathVariable String matchId){
        return scorerService.findByMatchId(matchId);
    }
}
