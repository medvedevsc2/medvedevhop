package com.example.medvedev.controllers;

import com.example.medvedev.model.Shoe;
import com.example.medvedev.service.ShoeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/shoes")
@AllArgsConstructor
public class ShoeController {

    private ShoeService shoeService;

    @GetMapping("/{id}")
    public Shoe findShoeById(@PathVariable int id) {
        return shoeService.findById(id);
    }

    @GetMapping
    public List<Shoe> showShoesByParameters(@RequestParam(required = false) String brand,
                                            @RequestParam(required = false) String model) {
        return shoeService.showShoes(brand, model);
    }
}