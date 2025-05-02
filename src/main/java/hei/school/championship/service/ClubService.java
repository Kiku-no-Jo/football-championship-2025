package hei.school.championship.service;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.PlayerCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {
    private final ClubCrudOperations clubCrudOperations;

    public List<Club> getAllClubs() {
        return clubCrudOperations.getAll(1, 100);
    }

    public Club getClubById(String idClub) {
        return clubCrudOperations.findById(idClub);
    }

    public List<Club> saveAll(List<Club> clubs) {
        return clubCrudOperations.saveAll(clubs);
    }


}
