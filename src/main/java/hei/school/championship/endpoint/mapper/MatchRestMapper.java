package hei.school.championship.endpoint.mapper;

import hei.school.championship.dao.mapper.PlayerMapper;
import hei.school.championship.dao.operations.MatchScorerCrudOperations;
import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.endpoint.rest.MatchResponse;
import hei.school.championship.entity.Match;
import hei.school.championship.entity.MatchScorer;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MatchRestMapper {
    private final PlayerMapper playerMapper;
    private final MatchScorerCrudOperations scorerCrudOperations;
    private final PlayerCrudOperations playerCrudOperations;

    public MatchResponse toResponse(Match match) {
        MatchResponse response = new MatchResponse();
        response.setId(match.getId());
        response.setStadium(match.getStadium());
        response.setMatchDatetime(match.getMatchDateTime());
        response.setActualStatus(match.getActualStatus());

        // Fetch and map scorers in one go
        List<MatchScorer> allScorers = scorerCrudOperations.findByMatchId(match.getId());

        response.setClubPlayingHome(buildMatchClub(
                match.getClubPlayingHome(),
                match.getHomeScore(),
                allScorers.stream()
                        .filter(s -> s.getClub().getId().equals(match.getClubPlayingHome().getId()))
                        .collect(Collectors.toList())
        ));

        response.setClubPlayingAway(buildMatchClub(
                match.getClubPlayingAway(),
                match.getAwayScore(),
                allScorers.stream()
                        .filter(s -> s.getClub().getId().equals(match.getClubPlayingAway().getId()))
                        .collect(Collectors.toList())
        ));

        return response;
    }

    private MatchResponse.MatchClub buildMatchClub(Club club, int score, List<MatchScorer> scorers) {
        MatchResponse.MatchClub matchClub = new MatchResponse.MatchClub();
        matchClub.setId(club.getId());
        matchClub.setName(club.getName());
        matchClub.setAcronym(club.getAcronym());
        matchClub.setScore(score);
        matchClub.setScorers(mapScorers(scorers));
        return matchClub;
    }

    private List<MatchResponse.Scorer> mapScorers(List<MatchScorer> matchScorers) {
        return matchScorers.stream()
                .filter(Objects::nonNull) // Filter null scorers
                .map(scorer -> {
                    MatchResponse.Scorer responseScorer = new MatchResponse.Scorer();
                    responseScorer.setMinuteOfGoal(scorer.getGoalTime());
                    responseScorer.setOwnGoal(scorer.isOwnGoal());

                    if (scorer.getPlayer() != null) {
                        MatchResponse.PlayerMinimalInfo playerInfo = new MatchResponse.PlayerMinimalInfo();
                        playerInfo.setId(scorer.getPlayer().getId());
                        playerInfo.setName(scorer.getPlayer().getName());
                        playerInfo.setNumber(scorer.getPlayer().getNumber());
                        responseScorer.setPlayer(playerInfo);
                    }

                    return responseScorer;
                })
                .collect(Collectors.toList());
    }
}
