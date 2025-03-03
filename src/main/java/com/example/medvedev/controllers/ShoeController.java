package com.example.medvedev.controllers;

import com.example.medvedev.exceptions.ShoeNotFoundException;
import com.example.medvedev.model.Shoe;
import com.example.medvedev.service.ShoeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shoes")
@AllArgsConstructor
public class ShoeController {

    private final ShoeService shoeService;

    @GetMapping("/{id}")
    public ResponseEntity<Shoe> findShoeById(@PathVariable int id) {
        Shoe shoe = shoeService.findById(id);
        if (shoe == null) {
            throw new ShoeNotFoundException("Shoe not found, id: " + id);
        }
        return ResponseEntity.ok(shoe);
    }

    @GetMapping
    public ResponseEntity<List<Shoe>> showShoesByParameters(@RequestParam(required = false) String brand,
                                                            @RequestParam(required = false) String model) {
        List<Shoe> shoes = shoeService.showShoes(brand, model);
        if (shoes.isEmpty()) {
            throw new ShoeNotFoundException("Shoe not found, brand, model: " + brand + ", " + model);
        }
        return ResponseEntity.ok(shoes);
    }
}

