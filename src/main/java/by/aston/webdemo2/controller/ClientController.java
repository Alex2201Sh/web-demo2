package by.aston.webdemo2.controller;

import by.aston.webdemo2.entity.n_plus_1.Client;
import by.aston.webdemo2.repository.jpa.ClientRepository;
import by.aston.webdemo2.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;
    private final ClientRepository clientRepository;
    @Autowired
    public ClientController(ClientService clientService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/fillDB")
    public String fillDataBase() {
        clientService.generateDB();
        return "Amount clients: " + clientRepository.count();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Client> findByNameContaining(@RequestParam String clientName) {
        List<Client> clients = clientService.findByNameContaining(clientName);
        return clients;
    }

}