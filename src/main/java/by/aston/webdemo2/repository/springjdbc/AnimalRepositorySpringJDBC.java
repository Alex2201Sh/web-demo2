package by.aston.webdemo2.repository.springjdbc;


import by.aston.webdemo2.entity.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepositorySpringJDBC {
    List<Animal> findAll();

    Optional<Animal> findById(int id);

    List<Animal> getAnimalsByBreed(String breed);

    Animal save(Animal animal);

    Animal deleteById(Integer animalId);
}
