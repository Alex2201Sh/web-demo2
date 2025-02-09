package by.aston.webdemo2.entity;

import jakarta.persistence.*;
import lombok.*;

//LOMBOK annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//JPA annotations
@Entity
@Table(name = "animals")
//NamedQueries example
@NamedQueries({ @NamedQuery(name = "Animal.findByAge", query ="FROM Animal WHERE age > :age") })
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String breed;
    private Integer age;
    private String origin;
    private String gender;
}