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
public class CoachCrudOperations implements CrudOperationsAlt<Coach> {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CoachMapper coachMapper;

    @Override
    public List<Coach> getAll(int page, int size) {
        if (page <= 0) page = 1;
        if (size <= 0) size = 10;

        List<Coach> coaches = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, nationality from coach order by name asc limit ? offset ?")) {
            statement.setInt(1, size);
            statement.setInt(2, size * (page - 1));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Coach coach = coachMapper.apply(resultSet);
                    coaches.add(coach);
                }
                return coaches;
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Coach findById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "select id, name, nationality from coach where id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return coachMapper.apply(resultSet);
                }
                throw new NotFoundException("Coach.id=" + id + " not found");
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    @SneakyThrows
    @Override
    public List<Coach> saveAll(List<Coach> entities) {
        List<Coach> coaches = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement("insert into coach (name, nationality) values (?, ?, ?)"
                             + " on conflict (id) do nothing"
                             + " returning name, nationality");) {
            entities.forEach(entityToSave -> {
                try {
                    statement.setString(1, entityToSave.getName());
                    statement.setString(2, entityToSave.getNationality());
                    statement.addBatch(); // group by batch so executed as one query in database
                } catch (SQLException e) {
                    throw new ServerException(e);
                }
            });
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    coaches.add(coachMapper.apply(resultSet));
                }
            }
            return coaches;
        }
    }
}
