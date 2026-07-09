package com.yiseth.productapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.exception.ResourceNotFoundException;
import com.yiseth.productapi.service.ProductService;
import com.yiseth.productapi.util.ProductTestData;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProductService productService;

	@Test
	void shouldCreateProductSuccessfully() throws Exception {

		ProductRequest request = ProductTestData.request();

		ProductResponse response = ProductTestData.response();

		when(productService.create(any(ProductRequest.class))).thenReturn(response);

		mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isCreated());

	}

	@Test
	void shouldFindProductByIdSuccessfully() throws Exception {

		ProductResponse response = ProductTestData.response();

		when(productService.findById(1L)).thenReturn(response);

		mockMvc.perform(get("/api/products/1")).andExpect(status().isOk());
	}

	@Test
	void shouldUpdateProductSuccessfully() throws Exception {

		ProductRequest request = ProductTestData.updateRequest();

		ProductResponse response = ProductTestData.updatedResponse();

		when(productService.update(any(Long.class), any(ProductRequest.class))).thenReturn(response);

		mockMvc.perform(put("/api/products/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
	}

	@Test
	void shouldDeleteProductSuccessfully() throws Exception {

		doNothing().when(productService).delete(1L);

		mockMvc.perform(delete("/api/products/1")).andExpect(status().isNoContent());
	}

	@Test
	void shouldFindAllProductsSuccessfully() throws Exception {

		PageImpl<ProductResponse> page = new PageImpl<>(List.of(ProductTestData.response()));

		when(productService.findAll(any())).thenReturn(page);

		mockMvc.perform(get("/api/products")).andExpect(status().isOk());
	}

	@Test
	void shouldReturn404WhenProductNotFound() throws Exception {

		when(productService.findById(1L)).thenThrow(new ResourceNotFoundException("Producto no encontrado"));

		mockMvc.perform(get("/api/products/1")).andExpect(status().isNotFound());
	}

	@Test
	void shouldReturn400WhenRequestIsInvalid() throws Exception {

		ProductRequest request = new ProductRequest();

		mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
	}
}
