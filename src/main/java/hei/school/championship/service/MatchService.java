package hei.school.championship.service;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.MatchCrudOperations;
import hei.school.championship.dao.operations.SeasonCrudOperations;
import hei.school.championship.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final MatchCrudOperations matchCrudOperations;
    private final ClubCrudOperations clubCrudOperations;
    private final SeasonCrudOperations seasonCrudOperations;

    public List<Match> findBySeasonYear(int seasonYear) {
        return matchCrudOperations.findBySeasonYear(seasonYear);
    }

    public List<Match> generateMatchesForSeason(int seasonYear, int page, int size) {
        // 1. Check if season exists
        Season season = seasonCrudOperations.findBySeasonYear(seasonYear);

        // 2. Validate season status
        if (!season.getStatus().equals(SeasonStatus.STARTED)) {
            throw new RuntimeException("Season " + seasonYear + " is not in STARTED state");
        }

        // 3. Check for existing matches
        if (!matchCrudOperations.findBySeasonYear(seasonYear).isEmpty()) {
            throw new RuntimeException("Matches already exist for season " + seasonYear);
        }

        // 4. Get all clubs
        List<Club> clubs = clubCrudOperations.getAll(page, size);
        List<Match> matchesToSave = new ArrayList<>();

        for (int i = 0; i < clubs.size(); i++) {
            for (int j = 0; j < clubs.size(); j++) {
                if (i != j) {
                    Club home = clubs.get(i);
                    Club away = clubs.get(j);

                    Match match = new Match();
                    match.setId(UUID.randomUUID().toString());
                    match.setClubPlayingHome(home);
                    match.setClubPlayingAway(away);
                    match.setStadium(home.getStadium());
                    match.setMatchDateTime(Timestamp.valueOf(LocalDateTime.now().plusDays(i + j)));
                    match.setActualStatus(MatchStatus.NOT_STARTED);
                    match.setHomeScore(0);
                    match.setAwayScore(0);
                    match.setSeasonYear(seasonYear);

                    matchesToSave.add(match);
                }
            }
        }

        // 5. Save all matches in a single batch
        return matchCrudOperations.saveAll(matchesToSave);
    }



}
