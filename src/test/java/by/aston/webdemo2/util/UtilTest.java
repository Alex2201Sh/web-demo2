package by.aston.webdemo2.util;

import by.aston.webdemo2.entity.Animal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UtilTest {

    @Test
    void getAnimals() {
        List<Animal> animals = Util.getAnimals();
        Assertions.assertThat(animals).hasSizeGreaterThanOrEqualTo(0);
    }
}