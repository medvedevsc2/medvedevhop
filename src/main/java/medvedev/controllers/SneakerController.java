package medvedev.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.get.GetSneakerDto;
import medvedev.services.SneakerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sneaker-controller", description = "CRUD operations for sneakers")
@RestController
@RequestMapping("/sneakers")
@RequiredArgsConstructor
public class SneakerController {

    private final SneakerService sneakerService;

    @Operation(summary = "Get sneaker by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sneaker found"),
            @ApiResponse(responseCode = "404", description = "Sneaker not found")
    })
    @GetMapping("/{id}")
    public GetSneakerDto getSneakerById(
            @Parameter(description = "ID of the sneaker to retrieve") @PathVariable long id) {
        return sneakerService.getSneakerById(id);
    }

    @Operation(summary = "Get all sneakers")
    @ApiResponse(responseCode = "200", description = "List of all sneakers")
    @GetMapping("/all")
    public List<GetSneakerDto> getAllSneakers() {
        return sneakerService.getAllSneakers();
    }

    @Operation(summary = "Get sneakers by brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sneakers filtered by brand"),
            @ApiResponse(responseCode = "404", description = "No sneakers found for the brand")
    })
    @GetMapping("/brand/{brand}")
    public List<GetSneakerDto> getSneakersByBrand(
            @Parameter(description = "Brand of sneakers") @PathVariable String brand) {
        return sneakerService.getSneakersByBrand(brand);
    }

    @Operation(summary = "Create a new sneaker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sneaker created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    public GetSneakerDto createSneaker(
            @Parameter(description = "Sneaker data to create") @RequestBody CreateSneakerDto sneakerDto) {
        return sneakerService.createSneaker(sneakerDto);
    }

    @Operation(summary = "Update a sneaker by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sneaker updated successfully"),
            @ApiResponse(responseCode = "404", description = "Sneaker not found")
    })
    @PutMapping("/{id}")
    public GetSneakerDto updateSneaker(
            @Parameter(description = "ID of the sneaker to update") @PathVariable Long id,
            @RequestBody CreateSneakerDto sneakerDto) {
        return sneakerService.updateSneaker(id, sneakerDto);
    }

    @Operation(summary = "Delete sneaker by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sneaker deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Sneaker not found")
    })
    @DeleteMapping("/{id}")
    public void deleteSneaker(
            @Parameter(description = "ID of the sneaker to delete") @PathVariable Long id) {
        sneakerService.deleteSneaker(id);
    }
}
