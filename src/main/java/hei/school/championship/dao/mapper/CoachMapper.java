package hei.school.championship.dao.mapper;

import hei.school.championship.entity.Coach;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CoachMapper implements Function<ResultSet, Coach> {
    @SneakyThrows
    @Override
    public Coach apply(ResultSet resultSet) {
        Coach coach = new Coach();
        coach.setName(resultSet.getString("name"));
        coach.setNationality(resultSet.getString("nationality"));
        return null;
    }
}
