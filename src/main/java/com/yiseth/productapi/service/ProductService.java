package com.yiseth.productapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;

public interface ProductService {

	ProductResponse create(ProductRequest request);

	ProductResponse update(Long id, ProductRequest request);

	ProductResponse findById(Long id);

	Page<ProductResponse> findAll(Pageable pageable);

	void delete(Long id);

}