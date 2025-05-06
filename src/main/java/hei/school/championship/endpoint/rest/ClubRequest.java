package hei.school.championship.endpoint.rest;

import hei.school.championship.entity.Coach;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClubRequest {
    private String id;
    private String name;
    private String acronym;
    private int yearCreation;
    private String stadium;
    private Coach coach;
}
