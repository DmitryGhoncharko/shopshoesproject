package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProduct {
    private Long id;
    private User user;
    private Product product;
    private Integer count;
    private Double finalPrice;
}
