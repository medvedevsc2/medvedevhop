package com.example.medvedev.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Shoe {
    private int id;
    private String brand;
    private String model;
    private int size;
    private double price;
    private String description;
}