package by.aston.webdemo2.repository.jpa;

import by.aston.webdemo2.entity.n_plus_1.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFullNameContaining(String name);

}