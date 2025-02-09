package by.aston.webdemo2.util;

import by.aston.webdemo2.entity.Animal;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;


public class Util {
    public static final String animalsDataFileName = "src\\main\\resources\\json\\animals.json";
    public static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @SneakyThrows
    public static List<Animal> getAnimals() {
        try {
            return newMapper().readValue(new File(animalsDataFileName), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setDateFormat(df);
        mapper.setLocale(Locale.ENGLISH);
        mapper.registerModule(new JSR310Module());

        return mapper;
    }
}
