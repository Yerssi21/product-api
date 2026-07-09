package com.yiseth.productapi.util;

import java.math.BigDecimal;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.entity.Product;

public final class ProductTestData {

	private ProductTestData() {
	}

	public static ProductRequest request() {

		ProductRequest request = new ProductRequest();

		request.setName("Laptop");
		request.setDescription("Lenovo ThinkPad");
		request.setPrice(new BigDecimal("2500"));
		request.setStock(10);

		return request;
	}

	public static ProductRequest updateRequest() {

		ProductRequest request = new ProductRequest();

		request.setName("Laptop Gamer");
		request.setDescription("Asus ROG");
		request.setPrice(new BigDecimal("3500"));
		request.setStock(5);

		return request;
	}

	public static Product product() {

		Product product = new Product();

		product.setName("Laptop");
		product.setDescription("Lenovo ThinkPad");
		product.setPrice(new BigDecimal("2500"));
		product.setStock(10);

		return product;
	}

	public static Product savedProduct() {

		Product product = product();

		product.setId(1L);

		return product;
	}

	public static Product updatedProduct() {

		Product product = new Product();

		product.setId(1L);
		product.setName("Laptop Gamer");
		product.setDescription("Asus ROG");
		product.setPrice(new BigDecimal("3500"));
		product.setStock(5);

		return product;
	}

	public static ProductResponse response() {

		ProductResponse response = new ProductResponse();

		response.setId(1L);
		response.setName("Laptop");
		response.setDescription("Lenovo ThinkPad");
		response.setPrice(new BigDecimal("2500"));
		response.setStock(10);

		return response;
	}

	public static ProductResponse updatedResponse() {

		ProductResponse response = new ProductResponse();

		response.setId(1L);
		response.setName("Laptop Gamer");
		response.setDescription("Asus ROG");
		response.setPrice(new BigDecimal("3500"));
		response.setStock(5);

		return response;
	}

}