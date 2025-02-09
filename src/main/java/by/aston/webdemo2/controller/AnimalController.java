package by.aston.webdemo2.controller;

import by.aston.webdemo2.entity.Animal;
import by.aston.webdemo2.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
@RequiredArgsConstructor
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping
    public List<Animal> findAll(@RequestParam(required = false) Integer id) {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    public Animal findByIdInPath(@PathVariable int id) {
        return animalService.findById(id);
    }

    @GetMapping("/params")
    public Animal findByIdInParams(@RequestParam(required = false) Integer id) {
        return animalService.findById(id);
    }


    @GetMapping("/breed/{breed}")
    public List<Animal> getAnimalsByBreed(@PathVariable String breed) {
        return animalService.getAnimalsByBreed(breed);
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        return animalService.save(animal);
    }

    @PatchMapping("/{id}")
    public Animal update(@RequestBody Animal animal, @PathVariable Integer id) {
        return animalService.update(animal, id);
    }

    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable Integer id) {
        return animalService.delete(id);
    }
}
