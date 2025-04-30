package hei.school.championship.dao.mapper;

import hei.school.championship.entity.Club;
import hei.school.championship.entity.Coach;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClubMapper implements Function<ResultSet, Club> {

    @SneakyThrows
    @Override
    public Club apply(ResultSet resultSet) {

        Club club = new Club();
        club.setId(resultSet.getString("id"));
        club.setName(resultSet.getString("name"));
        club.setAcronym(resultSet.getString("acronym"));
        club.setYearCreation(resultSet.getInt("year_creation"));

        Coach coach = new Coach();
        coach.setName(resultSet.getString("coach_name"));
        coach.setNationality(resultSet.getString("coach_nationality"));
        return null;
    }
}
