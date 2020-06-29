package br.com.emmanuel.kiametis.api.error;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

/**
 * APIError
 */
@Validated class APIError {
	
	private Instant referenceDate;

	private Integer errorCode;

	private String errorMessage;

	private Integer detailErrorCode;

	private String detailErrorMessage;

	private Map<String, Object> errors;
	
	private HttpStatus status;
	
	private APIError () {}

	public Instant getReferenceDate() {
		return referenceDate;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Integer getDetailErrorCode() {
		return detailErrorCode;
	}

	public String getDetailErrorMessage() {
		return detailErrorMessage;
	}

	public Map<String, Object> getErrors() {
		return errors;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
	private void setReferenceDate(Instant referenceDate) {
		this.referenceDate = referenceDate;
	}

	private void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	private void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private void setDetailErrorCode(Integer detailErrorCode) {
		this.detailErrorCode = detailErrorCode;
	}

	private void setDetailErrorMessage(String detailErrorMessage) {
		this.detailErrorMessage = detailErrorMessage;
	}

	private void setErrors(Map<String, Object> errors) {
		this.errors = errors;
	}
	
	private void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public static APIError newInstance (Integer errorCode, String errorMessage) {
		APIError error = new APIError();
		error.setErrorCode(errorCode);
		error.setErrorMessage(errorMessage);
		error.setReferenceDate(Instant.now());
		return error;
	}

	public APIError buildDetailError (Integer detailErrorCode, String detailErrorMessage) {
		setDetailErrorCode(detailErrorCode);
		setDetailErrorMessage(detailErrorMessage);
		return this;
	}
	
	public APIError buildDetailError (Integer detailErrorCode, String detailErrorMessage, Map<String, Object> errors) {
		setDetailErrorCode(detailErrorCode);
		setDetailErrorMessage(detailErrorMessage);
		setErrors(errors);
		return this;
	}
	
	public APIError buildStatus (HttpStatus status) {
		setStatus(status);
		return this;
	}
}