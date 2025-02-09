package by.aston.webdemo2.entity.relationships;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@ToString
public class UserProfile {
    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}