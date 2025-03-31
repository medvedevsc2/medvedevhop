package medvedev.controllers;

import medvedev.dao.create.CreateSneakerDto;
import medvedev.dao.get.GetSneakerDto;
import medvedev.services.SneakerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
