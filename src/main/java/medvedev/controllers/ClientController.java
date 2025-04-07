package medvedev.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateClientDto;
import medvedev.dao.get.GetClientDto;
import medvedev.services.ClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public GetClientDto createClient(@RequestBody CreateClientDto createClientDto) {
        return clientService.createClient(createClientDto);
    }

    @GetMapping("/{id}")
    public GetClientDto getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping
    public List<GetClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @PutMapping("/{id}")
    public GetClientDto updateClient(@PathVariable Long id, @RequestBody
        CreateClientDto createClientDto) {
        return clientService.updateClient(id, createClientDto);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}
