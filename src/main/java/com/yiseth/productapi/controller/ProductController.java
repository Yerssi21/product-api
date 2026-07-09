package com.yiseth.productapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiseth.productapi.dto.ProductRequest;
import com.yiseth.productapi.dto.ProductResponse;
import com.yiseth.productapi.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@Operation(summary = "Crear un producto", description = "Registra un nuevo producto en la base de datos")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),
			@ApiResponse(responseCode = "400", description = "Solicitud inválida") })
	@PostMapping
	public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {

		ProductResponse response = productService.create(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@Operation(summary = "Buscar un producto", description = "Obtiene un producto mediante su identificador")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Producto encontrado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {

		ProductResponse response = productService.findById(id);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Listar productos", description = "Obtiene una lista paginada de productos")
	@ApiResponse(responseCode = "200", description = "Consulta realizada correctamente")
	@GetMapping
	public ResponseEntity<Page<ProductResponse>> findAll(@PageableDefault(size = 10, sort = "id") Pageable pageable) {

		Page<ProductResponse> response = productService.findAll(pageable);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Actualizar un producto", description = "Actualiza la información de un producto existente")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Producto actualizado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {

		ProductResponse response = productService.update(id, request);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Eliminar un producto", description = "Elimina un producto por su identificador")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Producto eliminado"),
			@ApiResponse(responseCode = "404", description = "Producto no encontrado") })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		productService.delete(id);

		return ResponseEntity.noContent().build();
	}
}