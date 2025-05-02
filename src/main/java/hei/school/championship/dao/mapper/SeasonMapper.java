package hei.school.championship.dao.mapper;

import hei.school.championship.entity.Season;
import hei.school.championship.entity.SeasonStatus;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SeasonMapper implements Function<ResultSet, Season> {
    @SneakyThrows
    @Override
    public Season apply(ResultSet resultSet) {
        Season season = new Season();
        season.setYear(resultSet.getInt("year"));
        season.setAlias(resultSet.getString("alias"));
        season.setId(resultSet.getString("id"));
        season.setStatus(SeasonStatus.valueOf(resultSet.getString("status")));
        return season;
    }
}
