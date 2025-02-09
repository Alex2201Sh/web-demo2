package by.aston.webdemo2.repository.jdbc;

import by.aston.webdemo2.entity.Animal;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimalRepositoryJDBCImpl implements AnimalRepositoryJDBC {

    private static final String SQL_SELECT_ALL_ANIMALS = "SELECT id, breed, age, origin, gender FROM animals;";
    private static final String SQL_SELECT_ANIMAL_BY_ID = "SELECT id, breed, age, origin, gender FROM animals WHERE id = ?;";
    private static final String SQL_SELECT_ANIMAL_BY_BREED = "SELECT id, breed, age, origin, gender FROM animals WHERE breed = ?;";
    private static final String SQL_INSERT_ANIMAL = "INSERT INTO animals (breed, age, origin, gender) VALUES (?, ?, ?, ?);";
    private static final String SQL_UPDATE_ANIMAL = "UPDATE animals SET breed=?, age=?, origin=?, gender=? WHERE id=?;";
    private static final String SQL_DELETE_ANIMAL = "DELETE FROM animals WHERE id = ?;";

    @Override
    public List<Animal> findAll() {
        List<Animal> resultList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_ALL_ANIMALS);
            while (resultSet.next()) {
                resultList.add(new Animal(
                        resultSet.getInt("id"),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    @Override
    public Optional<Animal> findById(int id) {
        Animal animal = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ANIMAL_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                animal = new Animal();
                animal.setId(resultSet.getInt(1));
                animal.setBreed(resultSet.getString(2));
                animal.setAge(resultSet.getInt(3));
                animal.setOrigin(resultSet.getString(4));
                animal.setGender(resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return Optional.ofNullable(animal);
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) {
        List<Animal> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_SELECT_ANIMAL_BY_BREED);
            statement.setString(1, breed);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                resultList.add(new Animal(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }


    @Override
    public Animal save(Animal animal) {
        Integer id = animal.getId();
        String sqlQuery = id == null ? SQL_INSERT_ANIMAL : SQL_UPDATE_ANIMAL;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, animal.getBreed());
            statement.setInt(2, animal.getAge());
            statement.setString(3, animal.getOrigin());
            statement.setString(4, animal.getGender());
            if (id != null) statement.setInt(5, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0 && id == null) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        id = generatedKeys.getInt(1);
                    }
                }
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return findById(id).orElseGet(() -> new Animal());
    }

    @Override
    public Animal deleteById(Integer animalId) {
        Connection connection = null;
        PreparedStatement statement = null;
        Animal byId = findById(animalId).orElseGet(() -> new Animal());
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.prepareStatement(SQL_DELETE_ANIMAL);
            statement.setInt(1, animalId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return byId;
    }
}
