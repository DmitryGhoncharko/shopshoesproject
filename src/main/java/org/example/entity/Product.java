package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Long id;
    private String name;
    private String country;
    private String category;
    private String color;
    private String material;
    private String type;
    private Double price;
    private Double defaultPrice;
    private Integer count;
}
