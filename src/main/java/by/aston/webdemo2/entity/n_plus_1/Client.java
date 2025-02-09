package by.aston.webdemo2.entity.n_plus_1;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<EmailAddress> emailAddresses;

    public Client(String fullName, String mobileNumber, List<EmailAddress> emailAddresses) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.emailAddresses = emailAddresses;
    }
}