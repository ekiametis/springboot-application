package br.com.emmanuel.kiametis.api.model.customer;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CustomerUpdate {

	@JsonProperty("fullname")
	private String fullname;

	/**
	 * Customer fullname
	 * 
	 * @return identifier
	 **/
	@ApiModelProperty(required = true, value = "Customer fullname")
	@NotNull
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	
}
