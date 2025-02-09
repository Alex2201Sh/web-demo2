package by.aston.webdemo2.repository.springjdbc;

import by.aston.webdemo2.entity.Animal;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnimalRepositorySpringJDBCImpl implements AnimalRepositorySpringJDBC {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ALL_ANIMALS = "SELECT id, breed, age, origin, gender FROM animals;";
    private static final String SQL_SELECT_ANIMAL_BY_ID = "SELECT id, breed, age, origin, gender FROM animals WHERE id = ?;";
    private static final String SQL_SELECT_ANIMAL_BY_BREED = "SELECT id, breed, age, origin, gender FROM animals WHERE breed = ?;";
    private static final String SQL_INSERT_ANIMAL = "INSERT INTO animals (breed, age, origin, gender) VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_ANIMAL = "UPDATE animals SET breed=?, age=?, origin=?, gender=? WHERE id=?;";
    private static final String SQL_DELETE_ANIMAL = "DELETE FROM animals WHERE id = ?;";

    @Override
    public List<Animal> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ANIMALS, animalRowMapper);
    }


    @Override
    public Optional<Animal> findById(int id) {
        Animal animal = jdbcTemplate.queryForObject(SQL_SELECT_ANIMAL_BY_ID, animalRowMapper, id);
        return Optional.of(animal);
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) {
        return jdbcTemplate.query(SQL_SELECT_ANIMAL_BY_BREED, animalRowMapper, breed);
    }

    @Override
    public Animal save(Animal animal) {
        Integer id = animal.getId();
        if (id == null) {
            PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement statement = con.prepareStatement(SQL_INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS);
                    statement.setString(1, animal.getBreed());
                    statement.setInt(2, animal.getAge());
                    statement.setString(3, animal.getOrigin());
                    statement.setString(4, animal.getGender());
                    return statement;
                }
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int affectedRows = jdbcTemplate.update(preparedStatementCreator, keyHolder);
            if (affectedRows > 0 && id == null) {
                id = (Integer) keyHolder.getKeys().get("id");
            }
        } else {
            Animal animalFromDB = findById(id).orElseGet(() -> new Animal());
            jdbcTemplate.update(SQL_UPDATE_ANIMAL,
                    animal.getBreed() != null ? animal.getBreed() : animalFromDB.getBreed(),
                    animal.getAge() != null ? animal.getAge() : animalFromDB.getAge(),
                    animal.getOrigin() != null ? animal.getOrigin() : animalFromDB.getOrigin(),
                    animal.getGender() != null ? animal.getGender() : animalFromDB.getGender(),
                    id);
        }
        return findById(id).orElseGet(() -> new Animal());
    }

    @Override
    public Animal deleteById(Integer animalId) {
        Animal animal = findById(animalId).orElseGet(() -> new Animal());
        jdbcTemplate.update(SQL_DELETE_ANIMAL, animalId);
        return animal;
    }

    private final RowMapper<Animal> animalRowMapper = (resultSet, rowNum) -> new Animal(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getInt(3),
            resultSet.getString(4),
            resultSet.getString(5));
}
