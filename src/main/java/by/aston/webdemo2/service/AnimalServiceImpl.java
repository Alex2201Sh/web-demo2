package by.aston.webdemo2.service;

import by.aston.webdemo2.entity.Animal;
import by.aston.webdemo2.repository.jdbc.AnimalRepositoryJDBC;
import by.aston.webdemo2.repository.jpa.AnimalRepositoryJPA;
import by.aston.webdemo2.repository.orm.AnimalRepositoryHibernate;
import by.aston.webdemo2.repository.springjdbc.AnimalRepositorySpringJDBC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepositoryJPA repository;
//    private final AnimalRepositoryJDBC repository;
//    private final AnimalRepositorySpringJDBC repository;
//    private final AnimalRepositoryHibernate repository;

    @Override
    public List<Animal> findAll() {
        return repository.findAll();
    }

    @Override
    public Animal findById(int id) {
        return repository.findById(id).orElseGet(() -> new Animal());
    }

    @Override
    public List<Animal> getAnimalsByBreed(String breed) {
        return repository.getAnimalsByBreed(breed);
    }

    @Override
    public Animal save(Animal animal) {
        return repository.save(animal);
    }

    @Override
    public Animal update(Animal animal, Integer id) {
        animal.setId(id);
        return repository.save(animal);
    }

    @Override
    public Animal delete(Integer animalId) {
        Animal animal = repository.findById(animalId).orElseGet(() -> new Animal());
        repository.deleteById(animalId);
        return animal;
    }
}
