package hei.school.championship.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeHistory {
    private String id;
    private Player player;
    private Club fromClub;
    private Club toClub;
    @Setter
    private Timestamp transferDate;

}
