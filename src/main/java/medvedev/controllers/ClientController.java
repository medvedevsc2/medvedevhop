package medvedev.controllers;

import medvedev.dao.create.CreateClientDto;
import medvedev.dao.get.GetClientDto;
import medvedev.services.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
