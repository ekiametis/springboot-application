package br.com.emmanuel.kiametis.api.service.city;

enum CityError {
	INTERNAL_SERVER_ERROR(0, "Internal server error"),
	NOT_FOUND(1, "City not found");

	private Integer detailErrorCode;
	private String detailErrorMessage;

	private CityError(Integer detailErrorCode, String detailErrorMessage) {
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