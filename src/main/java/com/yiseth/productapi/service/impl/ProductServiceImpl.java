package com.yiseth.productapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.entity.Product;
import com.yiseth.productapi.exception.ResourceNotFoundException;
import com.yiseth.productapi.mapper.ProductMapper;
import com.yiseth.productapi.repository.ProductRepository;
import com.yiseth.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private final ProductMapper productMapper;

	@Override
	public ProductResponse create(ProductRequest request) {

		Product product = productMapper.toEntity(request);

		Product savedProduct = productRepository.save(product);

		return productMapper.toResponse(savedProduct);
	}

	@Override
	public ProductResponse update(Long id, ProductRequest request) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));

		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());

		Product updatedProduct = productRepository.save(product);

		return productMapper.toResponse(updatedProduct);
	}

	@Override
	public ProductResponse findById(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));

		return productMapper.toResponse(product);
	}

	@Override
	public Page<ProductResponse> findAll(Pageable pageable) {

		return productRepository.findAll(pageable).map(productMapper::toResponse);
	}

	@Override
	public void delete(Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Producto con id " + id + " no encontrado"));

		productRepository.delete(product);
	}

}