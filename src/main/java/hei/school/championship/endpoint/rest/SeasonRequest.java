package hei.school.championship.endpoint.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeasonRequest {
    private int year;
    private String alias;
}
