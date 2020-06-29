package br.com.emmanuel.kiametis.api.error;

import java.time.Instant;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class ResponseException extends Exception{

	private APIError apiError;

	private ResponseException(APIError apiError) {
		setApiError(apiError);
	}

	private APIError getApiError() {
		return apiError;
	}

	private void setApiError(APIError apiError) {
		this.apiError = apiError;
	}

	/**
	 * Date when the error occurs
	 * 
	 * @return referenceDate
	 **/
	@ApiModelProperty(required = true, value = "Date when the error occurs.")
	@NotNull
	@Valid
	@JsonProperty("referenceDate")
	public Instant getReferenceDate() {
		return getApiError().getReferenceDate();
	}
	
	/**
	 * Number that represents a range of the error code.
	 * 
	 * @return errorCode
	 **/
	@ApiModelProperty(required = true, value = "Number that represents a range of the error code.")
	@NotNull
	@Valid
	@JsonProperty("errorCode")
	public Integer getErrorCode() {
		return getApiError().getErrorCode();
	}

	/**
	 * Error message.
	 * 
	 * @return errorMessage
	 **/
	@ApiModelProperty(required = true, value = "Error message.")
	@NotNull
	@JsonProperty("errorMessage")
	public String getErrorMessage() {
		return getApiError().getErrorMessage();
	}
	
	/**
	 * Detail error code.
	 * 
	 * @return detailErrorCode
	 **/
	@ApiModelProperty(value = "Detail error code.")
	@Valid
	@JsonProperty("detailErrorCode")
	public Integer getDetailErrorCode() {
		return getApiError().getDetailErrorCode();
	}

	/**
	 * Detail message error.
	 * 
	 * @return detailErrorMessage
	 **/
	@ApiModelProperty(value = "Detail message error.")
	@JsonProperty("detailErrorMessage")
	public String getDetailErrorMessage() {
		return getApiError().getDetailErrorMessage();
	}
	
	/**
	 * Errors mapped by a Key - Value.
	 * 
	 * @return errors
	 **/
	@ApiModelProperty(value = "Errors mapped by a Key - Value.")
	@JsonProperty("errors")
	public Map<String, Object> getErrors() {
		return getApiError().getErrors();
	}
	
	/**
	 * Http status code
	 * 
	 * @return status
	 **/
	@ApiModelProperty(value = "Http status code.")
	@JsonProperty("status")
	public Integer getStatus() {
		return getApiError().getStatus().value();
	}
	
	public static ResponseException newInstance(Integer errorCode, String errorMessage) {
		ResponseException responseException = new ResponseException(
				APIError.newInstance(errorCode, errorMessage));
		return responseException;
	}
	
	public static ResponseException newInstance(Integer errorCode, String errorMessage, Integer detailErrorCode, String detailErrorMessage) {
		APIError error = APIError.newInstance(errorCode, errorMessage)
									.buildDetailError(detailErrorCode, detailErrorMessage);
		ResponseException responseException = new ResponseException(error);
		return responseException;
	}
	
	public static ResponseException newInstance(Integer errorCode, String errorMessage, Integer detailErrorCode, String detailErrorMessage, Map<String, Object> errors) {
		APIError error = APIError.newInstance(errorCode, errorMessage)
									.buildDetailError(detailErrorCode, detailErrorMessage, errors);
		ResponseException responseException = new ResponseException(error);
		return responseException;
	}
	
	public ResponseException addDetailError(Integer detailErrorCode, String detailErrorMessage) {
		setApiError(getApiError().buildDetailError(detailErrorCode, detailErrorMessage));
		return this;
	}
	
	public ResponseException addDetailError(Integer detailErrorCode, String detailErrorMessage, Map<String, Object> errors) {
		setApiError(getApiError().buildDetailError(detailErrorCode, detailErrorMessage, errors));
		return this;
	}
	
	public ResponseException addStatus(Integer status) {
		setApiError(getApiError().buildStatus(HttpStatus.valueOf(status)));
		return this;
	}

}
