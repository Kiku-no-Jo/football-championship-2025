package hei.school.championship.service;

import hei.school.championship.dao.operations.ClubCrudOperations;
import hei.school.championship.dao.operations.CoachCrudOperations;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Coach;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {
    private final CoachCrudOperations coachCrudOperations;

    public List<Coach> getAllClubs(int page, int size) {
        return coachCrudOperations.getAll(page, size);
    }

    public Coach getCoachById(int idCoach) {
        return coachCrudOperations.findById(idCoach);
    }

    public List<Coach> saveAll(List<Coach> coaches) {
        return coachCrudOperations.saveAll(coaches);
    }
}
