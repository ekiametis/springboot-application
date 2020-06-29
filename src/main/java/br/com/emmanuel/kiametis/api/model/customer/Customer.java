package br.com.emmanuel.kiametis.api.model.customer;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.emmanuel.kiametis.api.model.city.City;
import io.swagger.annotations.ApiModelProperty;

@Document(collection = "customer")
@Validated
public class Customer {
	
	@Indexed(unique = true, name = "identifier_index")
	@JsonProperty("identifier")
	private String identifier;

	@JsonProperty("fullname")
	private String fullname;
	
	@JsonProperty("gender")
	private Gender gender;

	@JsonProperty("birthday")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate birthday;

	@JsonProperty("city")
	private City city = null;
	
	private Customer () {}

	/**
	 * Customer identifier
	 * 
	 * @return identifier
	 **/
	@ApiModelProperty(required = true, value = "Customer identifier")
	@NotNull
	@Pattern(regexp = "^\\d{11}$")
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Fullname
	 * 
	 * @return fullname
	 **/
	@ApiModelProperty(required = true, value = "Fullname")
	@NotNull
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * Gender
	 * 
	 * @return gender
	 **/
	@ApiModelProperty(required = true, value = "Gender")
	@NotNull
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Get birthday
	 * 
	 * @return birthday
	 **/
	@ApiModelProperty(required = true, value = "Birthday")
	@NotNull
	@Valid
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	/**
	 * Get city
	 * 
	 * @return city
	 **/
	@ApiModelProperty(required = true, value = "City")
	@NotNull
	@Valid
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	
}
