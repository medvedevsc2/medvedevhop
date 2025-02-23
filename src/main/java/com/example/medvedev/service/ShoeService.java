package com.example.medvedev.service;

import com.example.medvedev.model.Shoe;
import java.util.List;

public interface ShoeService {

    Shoe findById(int id);

    List<Shoe> showShoes(String brand, String model);
}