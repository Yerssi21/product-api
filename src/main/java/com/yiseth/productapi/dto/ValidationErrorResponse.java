package com.yiseth.productapi.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationErrorResponse {

	private LocalDateTime timestamp;

	private Integer status;

	private String error;

	private String message;

	private String path;

	private Map<String, String> errors;

}