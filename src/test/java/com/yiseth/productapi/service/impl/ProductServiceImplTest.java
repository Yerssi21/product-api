package com.yiseth.productapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.entity.Product;
import com.yiseth.productapi.exception.ResourceNotFoundException;
import com.yiseth.productapi.mapper.ProductMapper;
import com.yiseth.productapi.repository.ProductRepository;
import com.yiseth.productapi.util.ProductTestData;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ProductMapper productMapper;

	@InjectMocks
	private ProductServiceImpl productService;

	@Test
	void shouldCreateProductSuccessfully() {

		ProductRequest request = ProductTestData.request();
		Product product = ProductTestData.product();
		Product savedProduct = ProductTestData.savedProduct();
		ProductResponse response = ProductTestData.response();

		when(productMapper.toEntity(request)).thenReturn(product);

		when(productRepository.save(product)).thenReturn(savedProduct);

		when(productMapper.toResponse(savedProduct)).thenReturn(response);

		ProductResponse result = productService.create(request);

		assertNotNull(result);
		assertEquals(response.getId(), result.getId());
		assertEquals(response.getName(), result.getName());
		assertEquals(response.getPrice(), result.getPrice());
		assertEquals(response.getStock(), result.getStock());

		verify(productMapper).toEntity(request);
		verify(productRepository).save(product);
		verify(productMapper).toResponse(savedProduct);
	}

	@Test
	void shouldFindProductByIdSuccessfully() {

		Long id = 1L;

		Product product = ProductTestData.savedProduct();
		ProductResponse response = ProductTestData.response();

		when(productRepository.findById(id)).thenReturn(Optional.of(product));

		when(productMapper.toResponse(product)).thenReturn(response);

		ProductResponse result = productService.findById(id);

		assertNotNull(result);
		assertEquals(response.getId(), result.getId());
		assertEquals(response.getName(), result.getName());

		verify(productRepository).findById(id);
		verify(productMapper).toResponse(product);
	}

	@Test
	void shouldThrowExceptionWhenProductNotFound() {

		Long id = 1L;

		when(productRepository.findById(id)).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> productService.findById(id));

		assertEquals("Producto con id 1 no encontrado", exception.getMessage());

		verify(productRepository).findById(id);
	}

	@Test
	void shouldUpdateProductSuccessfully() {

		Long id = 1L;

		ProductRequest request = ProductTestData.updateRequest();

		Product product = ProductTestData.savedProduct();

		Product updatedProduct = ProductTestData.updatedProduct();

		ProductResponse response = ProductTestData.updatedResponse();

		when(productRepository.findById(id)).thenReturn(Optional.of(product));

		when(productRepository.save(product)).thenReturn(updatedProduct);

		when(productMapper.toResponse(updatedProduct)).thenReturn(response);

		ProductResponse result = productService.update(id, request);

		assertNotNull(result);
		assertEquals(response.getId(), result.getId());
		assertEquals(response.getName(), result.getName());
		assertEquals(response.getPrice(), result.getPrice());
		assertEquals(response.getStock(), result.getStock());

		verify(productRepository).findById(id);
		verify(productRepository).save(product);
		verify(productMapper).toResponse(updatedProduct);
	}

	@Test
	void shouldDeleteProductSuccessfully() {

		Long id = 1L;

		Product product = ProductTestData.savedProduct();

		when(productRepository.findById(id)).thenReturn(Optional.of(product));

		productService.delete(id);

		verify(productRepository).findById(id);
		verify(productRepository).delete(product);
	}

	@Test
	void shouldFindAllProductsSuccessfully() {

		Pageable pageable = PageRequest.of(0, 10);

		Product product = ProductTestData.savedProduct();
		ProductResponse response = ProductTestData.response();

		Page<Product> page = new PageImpl<>(List.of(product));

		when(productRepository.findAll(pageable)).thenReturn(page);

		when(productMapper.toResponse(product)).thenReturn(response);

		Page<ProductResponse> result = productService.findAll(pageable);

		assertNotNull(result);
		assertEquals(1, result.getTotalElements());
		assertEquals(response.getName(), result.getContent().get(0).getName());

		verify(productRepository).findAll(pageable);
	}

}