package hei.school.championship.service;

import hei.school.championship.dao.operations.MatchCrudOperations;
import hei.school.championship.entity.Match;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchCrudOperations matchCrudOperations;

    public List<Match> findBySeasonYear(int seasonYear) {
        return matchCrudOperations.findBySeasonYear(seasonYear);
    }

}
