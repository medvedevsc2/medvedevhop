package medvedev.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.get.GetSneakerDto;
import medvedev.services.SneakerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sneakers")
@RequiredArgsConstructor
public class SneakerController {

    private final SneakerService sneakerService;

    @GetMapping("/{id}")
    public GetSneakerDto getSneakerById(@PathVariable long id) {
        return sneakerService.getSneakerById(id);
    }

    @GetMapping("/all")
    public List<GetSneakerDto> getAllSneakers() {
        return sneakerService.getAllSneakers();
    }

    @GetMapping("/brand/{brand}")
    public List<GetSneakerDto> getSneakersByBrand(@PathVariable String brand) {
        return sneakerService.getSneakersByBrand(brand);
    }

    @PostMapping
    public GetSneakerDto createSneaker(@RequestBody CreateSneakerDto sneakerDto) {
        return sneakerService.createSneaker(sneakerDto);
    }

    @PutMapping("/{id}")
    public GetSneakerDto updateSneaker(@PathVariable Long id,
                                       @RequestBody CreateSneakerDto sneakerDto) {
        return sneakerService.updateSneaker(id, sneakerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
    }
}
