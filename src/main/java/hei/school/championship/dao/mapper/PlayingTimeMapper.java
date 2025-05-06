package hei.school.championship.dao.mapper;

import hei.school.championship.entity.DurationUnit;
import hei.school.championship.entity.PlayingTime;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PlayingTimeMapper implements Function<ResultSet, PlayingTime> {
    @SneakyThrows
    @Override
    public PlayingTime apply(ResultSet resultSet) {
        PlayingTime playingTime = new PlayingTime();
        playingTime.setValue(resultSet.getInt("value"));
        playingTime.setDurationUnit(DurationUnit.valueOf(resultSet.getString("time_unit")));

        return playingTime;
    }

}
