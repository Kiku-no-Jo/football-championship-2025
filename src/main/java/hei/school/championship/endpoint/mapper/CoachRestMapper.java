package hei.school.championship.endpoint.mapper;

import hei.school.championship.endpoint.rest.CoachRest;
import hei.school.championship.entity.Coach;
import org.springframework.stereotype.Component;

@Component
public class CoachRestMapper {
    public Coach toEntity(CoachRest rest) {
        Coach coach = new Coach();
        coach.setName(rest.getName());
        coach.setNationality(rest.getNationality());
        return coach;
    }

    public CoachRest toRest(Coach coach) {
        CoachRest rest = new CoachRest();
        rest.setName(coach.getName());
        rest.setNationality(coach.getNationality());
        return rest;
    }
}
