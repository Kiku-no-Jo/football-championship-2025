package hei.school.championship.service;

import hei.school.championship.dao.operations.MatchCrudOperations;
import hei.school.championship.dao.operations.MatchScorerCrudOperations;
import hei.school.championship.entity.Match;
import hei.school.championship.entity.MatchScorer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchScorerService {
    private final MatchScorerCrudOperations scorerCrudOperations;

    public List<MatchScorer> findByMatchId(String matchId) {
        return scorerCrudOperations.findByMatchId(matchId);
    }
}
