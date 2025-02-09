package by.aston.webdemo2.service;

import by.aston.webdemo2.entity.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> findAll();

    Animal findById(int id);

    List<Animal> getAnimalsByBreed(String breed);

    Animal save(Animal animal);
    Animal update(Animal animal, Integer id);

    Animal delete(Integer animalId);
}
