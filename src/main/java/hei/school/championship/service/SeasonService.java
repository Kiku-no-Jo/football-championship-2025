package hei.school.championship.service;

import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.dao.operations.SeasonCrudOperations;
import hei.school.championship.endpoint.rest.SeasonRequest;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.Season;
import hei.school.championship.entity.SeasonStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonCrudOperations seasonCrudOperations;

    public List<Season> getAllSeasons(int page, int size) {
        return seasonCrudOperations.getAll(page, size);
    }

    public Season getSeasonById(String idSeason) {
        return seasonCrudOperations.findById(idSeason);
    }

    public List<Season> saveAll(List<Season> seasons) {
        return seasonCrudOperations.saveAll(seasons);
    }

    public List<Season> createSeasons(List<SeasonRequest> seasonRequests) {
        // Get the latest ID number (e.g., 4 if the latest ID is "S04")
        int latestIdNum = seasonCrudOperations.findLatestSeasonIdNumber();

        List<Season> seasonsToSave = new ArrayList<>();
        for (SeasonRequest request : seasonRequests) {
            latestIdNum++; // Increment (4 → 5)
            String newId = "S" + String.format("%02d", latestIdNum); // Formats as "S05"

            Season season = new Season();
            season.setId(newId);
            season.setYear(request.getYear());
            season.setAlias(request.getAlias());
            season.setStatus(SeasonStatus.NOT_STARTED); // Default status
            seasonsToSave.add(season);
        }

        return seasonCrudOperations.saveAll(seasonsToSave);
    }
}
