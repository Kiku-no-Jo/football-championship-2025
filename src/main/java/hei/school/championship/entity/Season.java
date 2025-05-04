package hei.school.championship.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonPropertyOrder({"year", "alias", "id", "status"})
public class Season {
    private String id;
    private String alias;
    private int year;
    private SeasonStatus status;
}
