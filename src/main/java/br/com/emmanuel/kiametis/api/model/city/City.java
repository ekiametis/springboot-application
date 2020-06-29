package br.com.emmanuel.kiametis.api.model.city;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "city")
@Validated
public class City{
	
	@Id
	@JsonProperty("id")
	private String id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private State state;
	
	private City() {}

	/**
	 * City name
	 * 
	 * @return name
	 **/
	@ApiModelProperty(required = true, value = "City name")
	@NotNull
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * City state
	 * 
	 * @return state
	 **/
	@ApiModelProperty(required = true, value = "City state")
	@NotNull
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
}
