package hei.school.championship.endpoint.rest;

import hei.school.championship.entity.DurationUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DurationRequest {
    private int value;
    private DurationUnit durationUnit;
}
