package com.example.medvedev.service.impl;

import com.example.medvedev.model.Shoe;
import com.example.medvedev.repository.InMemoryShoeDAO;
import com.example.medvedev.service.ShoeService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InMemoryShoeServiceImpl implements ShoeService {

    private final InMemoryShoeDAO repository;

    @Override
    public Shoe findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Shoe> showShoes(String brand, String model) {
        return repository.showShoes(brand, model);
    }
}