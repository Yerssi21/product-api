package com.yiseth.productapi.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.entity.Product;
import com.yiseth.productapi.util.ProductTestData;

class ProductMapperTest {

    private ProductMapper productMapper;

    @BeforeEach
    void setUp() {
        productMapper = new ProductMapper();
    }

    @Test
    void shouldConvertRequestToEntity() {

        ProductRequest request = ProductTestData.request();

        Product product = productMapper.toEntity(request);

        assertNotNull(product);
        assertEquals(request.getName(), product.getName());
        assertEquals(request.getDescription(), product.getDescription());
        assertEquals(request.getPrice(), product.getPrice());
        assertEquals(request.getStock(), product.getStock());
    }

    @Test
    void shouldConvertEntityToResponse() {

        Product product = ProductTestData.savedProduct();

        ProductResponse response = productMapper.toResponse(product);

        assertNotNull(response);
        assertEquals(product.getId(), response.getId());
        assertEquals(product.getName(), response.getName());
        assertEquals(product.getDescription(), response.getDescription());
        assertEquals(product.getPrice(), response.getPrice());
        assertEquals(product.getStock(), response.getStock());
    }
}