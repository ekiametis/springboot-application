package br.com.emmanuel.kiametis.api.controller.city;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.emmanuel.kiametis.api.error.ResponseException;
import br.com.emmanuel.kiametis.api.model.city.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(value = "cities")
public interface CityControllerAPI {

	@ApiOperation(value = "List cities", nickname = "listCities", notes = "This resource list cities based in parameters.", response = City.class, tags = {
			"city-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cities listed with success.", response = City.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/cities", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Flux<City>> listCities(
			@ApiParam(value = "City name") @Valid @RequestParam(value = "name", required = false) String name,
			@ApiParam(value = "City state", allowableValues = "AC, AL, AP, AM, BA, CE, DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO") @Valid @RequestParam(value = "state", required = false) String state);

	@ApiOperation(value = "Retrieve a city", nickname = "retrieveCity", notes = "This resource retrieve a city based in the unique city identifier.", response = City.class, tags = {
			"city-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "City retrieved with success.", response = City.class),
			@ApiResponse(code = 404, message = "City not found.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/cities/{id}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Mono<City>> retrieveCity(
			@ApiParam(value = "Unique city identifier", required = true) @PathVariable("id") String id);

	@ApiOperation(value = "Insert city", nickname = "insertCity", notes = "EThis resource insert a new city.", response = City.class, tags = {
			"city-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "City inserted with success.", response = City.class),
			@ApiResponse(code = 400, message = "A bussiness error.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/cities", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Mono<City>> insertCity(
			@ApiParam(value = "City properties", required = true) @Valid @RequestBody City body);

}
