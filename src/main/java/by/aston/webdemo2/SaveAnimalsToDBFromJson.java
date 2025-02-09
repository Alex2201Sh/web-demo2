package by.aston.webdemo2;

import by.aston.webdemo2.entity.Animal;
import by.aston.webdemo2.repository.jdbc.ConnectionCreator;
import by.aston.webdemo2.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SaveAnimalsToDBFromJson {

    public static void main(String[] args) {
        StringBuilder sql = new StringBuilder("INSERT INTO animals (id , breed, age, origin, gender) VALUES \n");
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .forEach(animal -> {
                    sql.append("(")
                            .append(animal.getId()).append(",")
                            .append("'").append(animal.getBreed().replaceAll("'", "''")).append("'").append(",")
                            .append(animal.getAge()).append(",")
                            .append("'").append(animal.getOrigin()).append("'").append(",")
                            .append("'").append(animal.getGender()).append("'").append(")")
                            .append(",\n");
                });
        sql.replace(sql.length() - 2, sql.length() - 1, ";");

        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionCreator.createConnection();
            statement = connection.createStatement();
            statement.execute(sql.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
