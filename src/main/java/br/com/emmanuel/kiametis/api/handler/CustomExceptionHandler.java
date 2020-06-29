package br.com.emmanuel.kiametis.api.handler;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.emmanuel.kiametis.api.error.ErrorType;
import br.com.emmanuel.kiametis.api.error.ResponseException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private enum ErrorDetailType {
		INTERNAL_SERVER_ERROR(0, "Internal server error"),
		METHOD_NOT_ALLOWED(1, "Method not allowed"),
		MISSING_PARAMETER(2, "Invalid parameter"),
		CONSTRAINT_VIOLATION(3, "Constraint violation"),
		INVALID_TYPE(4, "Invalid type"),
		MALFORMED_JSON(5, "Malformed JSON");

		private Integer detailErrorCode;
		private String detailErrorMessage;

		private ErrorDetailType(Integer detailErrorCode, String detailErrorMessage) {
			this.detailErrorCode = detailErrorCode;
			this.detailErrorMessage = detailErrorMessage;
		}

		public Integer getDetailErrorCode() {
			return detailErrorCode;
		}

		public String getDetailErrorMessage() {
			return detailErrorMessage;
		}

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> errors = new HashMap<String, Object>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.put(error.getObjectName(), error.getDefaultMessage());
		}
		return handleException(ex, request, ErrorDetailType.METHOD_NOT_ALLOWED, errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put(ex.getParameterName(), ex.getParameterName() + " parameter is missing");
		return handleException(ex, request, ErrorDetailType.MISSING_PARAMETER, errors);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
       Map<String, Object> errors = new HashMap<String, Object>();
       errors.put("reason",  ex.getMessage());
       return handleException(ex, request, ErrorDetailType.MALFORMED_JSON, errors);
	}


	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		Map<String, Object> errors = new HashMap<String, Object>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.put(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath(),
					violation.getMessage());
		}
		return handleException(ex, request, ErrorDetailType.CONSTRAINT_VIOLATION, errors);
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put(ex.getName(), ex.getName() + " should be of type " + ex.getRequiredType().getName());
		return handleException(ex, request, ErrorDetailType.INVALID_TYPE, errors);
	}
	
	@ExceptionHandler({ ResponseException.class })
	public ResponseEntity<Object> handleAll(ResponseException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.valueOf(ex.getStatus()), request);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		Map<String, Object> errors = new HashMap<String, Object>();
		errors.put("Some error occurs", ex.getMessage());
		return handleException(ex, request, ErrorDetailType.INTERNAL_SERVER_ERROR, errors);
	}

	private ResponseEntity<Object> handleException(Exception ex, WebRequest request,
			ErrorDetailType errorDetail ,Map<String, Object> errors) {
		ResponseException responseException = ErrorType.INVALID_REQUEST.getResponseException();
		responseException.addDetailError(errorDetail.getDetailErrorCode(), errorDetail.getDetailErrorMessage(), errors).addStatus(HttpStatus.BAD_REQUEST.value());
		return handleExceptionInternal(ex, responseException, new HttpHeaders(), HttpStatus.valueOf(responseException.getStatus()), request);
	}
}
