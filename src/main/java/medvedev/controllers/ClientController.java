package medvedev.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateClientDto;
import medvedev.dao.get.GetClientDto;
import medvedev.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Client-controller", description = "CRUD operations for clients")
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Operation(summary = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public GetClientDto createClient(@RequestBody CreateClientDto createClientDto) {
        return clientService.createClient(createClientDto);
    }

    @Operation(summary = "Get client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @GetMapping("/{id}")
    public GetClientDto getClientById(
            @Parameter(description = "ID of the client") @PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @Operation(summary = "Get all clients")
    @ApiResponse(responseCode = "200", description = "List of all clients")
    @GetMapping
    public List<GetClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Update client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @PutMapping("/{id}")
    public GetClientDto updateClient(
            @Parameter(description = "ID of the client") @PathVariable Long id,
            @RequestBody CreateClientDto createClientDto) {
        return clientService.updateClient(id, createClientDto);
    }

    @Operation(summary = "Delete client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    @DeleteMapping("/{id}")
    public void deleteClient(
            @Parameter(description = "ID of the client") @PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @Operation(summary = "Get clients by sneaker brand")
    @ApiResponse(responseCode = "200", description = "Clients filtered by sneaker brand")
    @GetMapping("/by-sneaker-brand/{brand}")
    public List<GetClientDto> getClientsBySneakerBrand(
            @Parameter(description = "Brand of sneakers") @PathVariable String brand) {
        return clientService.getClientsBySneakerBrand(brand);
    }
}