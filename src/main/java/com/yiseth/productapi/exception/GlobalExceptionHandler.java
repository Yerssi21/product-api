package com.yiseth.productapi.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.yiseth.productapi.dto.ApiError;
import com.yiseth.productapi.dto.ValidationErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

		ApiError error = buildApiError(HttpStatus.NOT_FOUND, ex.getMessage(), request);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		ValidationErrorResponse response = ValidationErrorResponse.builder().timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value()).error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.message("Error de validación").path(request.getRequestURI()).errors(errors).build();

		return ResponseEntity.badRequest().body(response);
	}

	private ApiError buildApiError(HttpStatus status, String message, HttpServletRequest request) {

		return ApiError.builder().timestamp(LocalDateTime.now()).status(status.value()).error(status.getReasonPhrase())
				.message(message).path(request.getRequestURI()).build();
	}
}