package by.aston.webdemo2.entity.relationships;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String buildingNumber;

    private String flatNumber;

}
