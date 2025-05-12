package hei.school.championship.endpoint.rest;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStatsRequest {
    private int scoredGoals;
    private DurationRequest playingTime;
}
