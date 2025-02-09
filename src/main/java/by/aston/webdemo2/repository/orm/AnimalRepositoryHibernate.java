package by.aston.webdemo2.repository.orm;

import by.aston.webdemo2.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepositoryHibernate {
    List<Animal> findAll();

    Optional<Animal> findById(int id);

    List<Animal> getAnimalsByBreed(String breed);

    Animal save(Animal animal);

    Animal deleteById(Integer animalId);
}
