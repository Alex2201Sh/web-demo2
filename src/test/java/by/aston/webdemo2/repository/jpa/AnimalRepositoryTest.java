//package by.aston.webdemo2.repository.jpa;
//
//import by.aston.webdemo2.entity.Animal;
//import lombok.RequiredArgsConstructor;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//class AnimalRepositoryTest {
//
//    private final AnimalRepositoryJPA animalRepositoryJPA;
//
//    @Test
//    void findAll() {
//        List<Animal> all = animalRepositoryJPA.findAll();
//        Assertions.assertThat(all).hasSizeGreaterThanOrEqualTo(0);
//    }
//
//}