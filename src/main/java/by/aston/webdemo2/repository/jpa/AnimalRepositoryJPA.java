package by.aston.webdemo2.repository.jpa;

import by.aston.webdemo2.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepositoryJPA extends JpaRepository<Animal, Integer> {
    List<Animal> getAnimalsByBreed(String breed);

    @Query(nativeQuery = true, value = "SELECT * FROM animals where gender is null")
    List<Animal> getAnimalsWithoutGender();

}
