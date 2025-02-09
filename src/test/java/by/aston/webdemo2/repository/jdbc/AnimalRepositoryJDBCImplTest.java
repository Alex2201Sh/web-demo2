package by.aston.webdemo2.repository.jdbc;

import by.aston.webdemo2.entity.Animal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class AnimalRepositoryJDBCImplTest {

    private final AnimalRepositoryJDBC animalRepositoryJDBC =
            new AnimalRepositoryJDBCImpl();

    @BeforeEach
    void init() {
        Animal animal = new Animal(null, "test breed", 0, "", "");
        for (int i = 0; i < 10; i++) {
            animalRepositoryJDBC.save(animal);
        }
    }

    @AfterEach
    void cleanup() {
        animalRepositoryJDBC.getAnimalsByBreed("test breed")
                .forEach(animal -> animalRepositoryJDBC.deleteById(animal.getId()));
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            statement.execute("SELECT setval('animals_id_seq', max(id)) FROM animals;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void findAll() {
        List<Animal> all = animalRepositoryJDBC.findAll();
        Assertions.assertThat(all.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findById() {
        Animal anyAnimal = animalRepositoryJDBC.findAll().stream()
                .findAny()
                .orElseGet(null);
        Animal byId = animalRepositoryJDBC.findById(anyAnimal.getId()).orElseGet(() -> null);
        Assertions.assertThat(byId).isEqualTo(anyAnimal);
    }

    @Test
    void getAnimalsByBreed() {
        List<Animal> animalsByBreed = animalRepositoryJDBC.getAnimalsByBreed("test breed");
        Assertions.assertThat(animalsByBreed).hasSizeGreaterThanOrEqualTo(0);
    }

    @Test
    void save() {
        Animal animal = new Animal(null, "test breed", 10, "test origin",
                "test gender");
        Animal save = animalRepositoryJDBC.save(animal);
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(animalRepositoryJDBC.findById(save.getId()).get()).isEqualTo(save);
    }

    @Test
    void delete() {
        Animal anyAnimal = animalRepositoryJDBC.getAnimalsByBreed("test breed").stream().findAny()
                .orElseGet(null);
        animalRepositoryJDBC.deleteById(anyAnimal.getId());
        Assertions.assertThat(animalRepositoryJDBC.findById(anyAnimal.getId())).isNotEqualTo(anyAnimal);
    }
}