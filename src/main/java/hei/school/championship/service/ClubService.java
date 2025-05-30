package hei.school.championship.service;

import hei.school.championship.dao.operations.*;
import hei.school.championship.endpoint.rest.ClubRequest;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.ClubStats;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.TradeHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubCrudOperations clubCrudOperations;
    private final ClubStatsCrudOperations clubStatsCrudOperations;
    private final TradeHistoryCrudOperations tradeHistoryCrudOperations;

    public List<Club> getAllClubs(int page, int size) {
        return clubCrudOperations.getAll(page, size);
    }

    public Club getClubById(String idClub) {
        return clubCrudOperations.findById(idClub);
    }

    public List<Club> saveAll(List<Club> clubs) {
        return clubCrudOperations.saveAll(clubs);
    }

    public List<ClubStats> getClubStatsBySeasonYear(int seasonYear) {
        return  clubStatsCrudOperations.getAllBySeasonYear(seasonYear);
    }

    public List<TradeHistory> getTrades(int page, int size) {
        return tradeHistoryCrudOperations.getAll(page, size);
    }



    public List<Club> createOrUpdateClubs(List<ClubRequest> clubRequests) {
        // Convert ClubRequest to Club entities
        List<Club> clubsToSave = clubRequests.stream()
                .map(this::convertToClub)
                .collect(Collectors.toList());

        // Save to database
        return clubCrudOperations.saveAll(clubsToSave);
    }

    private Club convertToClub(ClubRequest request) {
        Club club = new Club();
        club.setId(request.getId());
        club.setName(request.getName());
        club.setAcronym(request.getAcronym());
        club.setYearCreation(request.getYearCreation());
        club.setStadium(request.getStadium());
        club.setCoach(request.getCoach());

        return club;
    }


}
