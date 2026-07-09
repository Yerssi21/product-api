package com.yiseth.productapi.dto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

	@Schema(description = "Nombre del producto", example = "Laptop Lenovo ThinkPad")
	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
	private String name;

	private String description;

	@Schema(description = "Precio del producto", example = "2599.99")
	@NotNull(message = "El precio es obligatorio")
	@DecimalMin(value = "0.01", message = "El precio debe ser mayor que cero")
	private BigDecimal price;

	@NotNull(message = "El stock es obligatorio")
	@Min(value = 0, message = "El stock no puede ser negativo")
	private Integer stock;

}