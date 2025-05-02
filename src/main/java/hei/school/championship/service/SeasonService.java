package hei.school.championship.service;

import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.dao.operations.SeasonCrudOperations;
import hei.school.championship.entity.Player;
import hei.school.championship.entity.Season;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonCrudOperations seasonCrudOperations;

    public List<Season> getAllSeasons() {
        return seasonCrudOperations.getAll(1, 100);
    }

    public Season getSeasonById(String idSeason) {
        return seasonCrudOperations.findById(idSeason);
    }

    public List<Season> saveAll(List<Season> seasons) {
        return seasonCrudOperations.saveAll(seasons);
    }
}
