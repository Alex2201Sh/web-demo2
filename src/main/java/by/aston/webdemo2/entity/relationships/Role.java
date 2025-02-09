package by.aston.webdemo2.entity.relationships;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
    @Id
    private Long id;
}
