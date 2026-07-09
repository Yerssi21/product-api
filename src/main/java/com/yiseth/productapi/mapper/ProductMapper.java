package com.yiseth.productapi.mapper;

import org.springframework.stereotype.Component;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.entity.Product;

@Component
public class ProductMapper {

	public Product toEntity(ProductRequest request) {

		Product product = new Product();

		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());

		return product;
	}

	public ProductResponse toResponse(Product product) {

		ProductResponse response = new ProductResponse();

		response.setId(product.getId());
		response.setName(product.getName());
		response.setDescription(product.getDescription());
		response.setPrice(product.getPrice());
		response.setStock(product.getStock());
		response.setCreatedAt(product.getCreatedAt());
		response.setUpdatedAt(product.getUpdatedAt());

		return response;
	}
}