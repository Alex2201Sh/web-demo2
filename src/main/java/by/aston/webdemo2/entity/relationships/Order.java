package by.aston.webdemo2.entity.relationships;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Entity
@Table(name = "orders")
public class Order {
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Id
    private Long id;
}