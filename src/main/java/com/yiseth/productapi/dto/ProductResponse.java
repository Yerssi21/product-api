package com.yiseth.productapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}