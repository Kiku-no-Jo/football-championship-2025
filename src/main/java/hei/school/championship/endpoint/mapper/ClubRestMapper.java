package hei.school.championship.endpoint.mapper;

import hei.school.championship.dao.operations.CoachCrudOperations;
import hei.school.championship.endpoint.rest.ClubRest;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Coach;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClubRestMapper {
    @Autowired
    private CoachRestMapper coachRestMapper;

    public ClubRest toRest(Club club) {
        ClubRest rest = new ClubRest();
        rest.setId(club.getId());
        rest.setName(club.getName());
        rest.setAcronym(club.getAcronym());
        rest.setYearCreation(club.getYearCreation());
        rest.setStadium(club.getStadium());
        rest.setCoach(coachRestMapper.toRest(club.getCoach()));
        return rest;
    }

    public Club toEntity(ClubRest rest, Coach savedCoach) {
        Club club = new Club();
        club.setId(rest.getId());
        club.setName(rest.getName());
        club.setAcronym(rest.getAcronym());
        club.setYearCreation(rest.getYearCreation());
        club.setStadium(rest.getStadium());
        club.setCoach(savedCoach); // coach has its ID after DB insert
        return club;
    }
}
