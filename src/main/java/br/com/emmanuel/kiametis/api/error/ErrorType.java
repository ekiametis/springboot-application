package br.com.emmanuel.kiametis.api.error;

public enum ErrorType {
	CITY_RETRIEVAL(1000, "Retrieve city"),
	CUSTOMER_RETRIEVAL(2000, "Retrieve customer"),
	CUSTOMER_UPDATE(3000, "Update customer"),
	CUSTOMER_DELETE(4000, "Delete customer"),
	INVALID_REQUEST(900000, "Request Error");
	
	private ResponseException apiError;
	
	private ErrorType(Integer errorCode, String errorMessage) {
		this.apiError = ResponseException.newInstance(errorCode, errorMessage);
	}

	public ResponseException getResponseException() {
		return apiError;
	}
}
