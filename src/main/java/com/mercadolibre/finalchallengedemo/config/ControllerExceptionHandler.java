package com.mercadolibre.finalchallengedemo.config;

import com.mercadolibre.finalchallengedemo.exceptions.ApiError;
import com.mercadolibre.finalchallengedemo.exceptions.ApiException;
import com.mercadolibre.finalchallengedemo.exceptions.InvalidPartFilterException;
import com.mercadolibre.finalchallengedemo.exceptions.PartsNotFoundException;
import com.newrelic.api.agent.NewRelic;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ApiError> noHandlerFoundException(HttpServletRequest req, NoHandlerFoundException ex) {
		ApiError apiError = new ApiError("route_not_found", String.format("Route %s not found", req.getRequestURI()), HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ExceptionHandler(value = { ApiException.class })
	protected ResponseEntity<ApiError> handleApiException(ApiException e) {
		Integer statusCode = e.getStatusCode();
		boolean expected = HttpStatus.INTERNAL_SERVER_ERROR.value() > statusCode;
		NewRelic.noticeError(e, expected);
		if (expected) {
			LOGGER.warn("Internal Api warn. Status Code: " + statusCode, e);
		} else {
			LOGGER.error("Internal Api error. Status Code: " + statusCode, e);
		}

		ApiError apiError = new ApiError(e.getCode(), e.getDescription(), statusCode);
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<ApiError> handleUnknownException(Exception e) {
		LOGGER.error("Internal error", e);
		NewRelic.noticeError(e);

		ApiError apiError = new ApiError("internal_error", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.status(apiError.getStatus())
				.body(apiError);
	}

	@ExceptionHandler(InvalidPartFilterException.class)
	public ResponseEntity handleInvalidDateException(InvalidPartFilterException e) {
		return handle(HttpStatus.BAD_REQUEST,e.getMessage());
	}

	@ExceptionHandler(PartsNotFoundException.class)
	public ResponseEntity handlePartsNotFoundedException(PartsNotFoundException e) {
		return handle(HttpStatus.NOT_FOUND,e.getMessage());
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ApiError> onMethodArgumentNotValidException(BindException ex) {
		return getResponseEntity(ex);
	}

	private ResponseEntity<ApiError> getResponseEntity(BindException ex) {
		Map<String, String> errors = new LinkedHashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		ApiError apiError = new ApiError("BAD_REQUEST", errors.toString(), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}

	private ResponseEntity<ApiError> handle(HttpStatus status, String message) {
		return new ResponseEntity<>(new ApiError(status.toString(), message, status.value() ), status);
	}
}