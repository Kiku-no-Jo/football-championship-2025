package hei.school.championship.endpoint.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubRest {
    private String id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private CoachRest coach;
}
