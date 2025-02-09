package by.aston.webdemo2.entity.relationships;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String users_id;

    private String name;
    private Integer age;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "user")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}