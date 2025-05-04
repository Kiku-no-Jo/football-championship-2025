package hei.school.championship.dao.operations;

import hei.school.championship.dao.DataSource;
import hei.school.championship.dao.mapper.ClubMapper;
import hei.school.championship.dao.mapper.CoachMapper;
import hei.school.championship.entity.Club;
import hei.school.championship.entity.Coach;
import hei.school.championship.service.exception.NotFoundException;
import hei.school.championship.service.exception.ServerException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClubCrudOperations implements CrudOperations<Club> {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ClubMapper clubMapper;

    @Override
    public List<Club> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<Club> clubs = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, acronym, year_creation, stadium, coach_id from club order by name asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Club club = clubMapper.apply(resultSet);
                    clubs.add(club);
                }
                return clubs;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Club findById(String id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, acronym, year_creation, stadium, coach_id from club where id = ?")) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return clubMapper.apply(resultSet);
                }
                throw new NotFoundException("Club.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @SneakyThrows
    @Override
    public List<Club> saveAll(List<Club> entities) {
        List<Club> clubs = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into club (id, name, acronym, year_creation, stadium, coach_id) values (?, ?, ?, ?, ?, ?)"
                             + " on conflict (id) do nothing"
                             + " returning id, name, acronym, yearCreation, stadium, coach_id");) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getId());
                    statement.setString(2, entityToSave.getName());
                    statement.setString(3, entityToSave.getAcronym());
                    statement.setInt(4, entityToSave.getYearCreation());
                    statement.setString(5, entityToSave.getStadium());
                    statement.setString(6, entityToSave.getCoach().getId());
                    statement.addBatch(); // group by batch so executed as one query in database
                } catch (SQLException e) {
                    throw new ServerException(e);
                }
            });
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    clubs.add(clubMapper.apply(resultSet));
                }
            }
            return clubs;
        }
    }


}
