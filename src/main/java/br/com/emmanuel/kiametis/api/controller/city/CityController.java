package br.com.emmanuel.kiametis.api.controller.city;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.emmanuel.kiametis.api.model.city.City;
import br.com.emmanuel.kiametis.api.model.city.State;
import br.com.emmanuel.kiametis.api.service.city.CityService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CityController implements CityControllerAPI {

	private static final Logger log = LoggerFactory.getLogger(CityController.class);

	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	
	@Autowired
    private CityService cityService;

	@Autowired
	public CityController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Flux<City>> listCities(String name, String state) {
		try {
			State stateEnum = State.fromValue(state);
			Flux<City> result = cityService.list(name, stateEnum);
			return new ResponseEntity<Flux<City>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Flux<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Mono<City>> retrieveCity(String id) {
		try {
			Mono<City> result = cityService.retrieve(id);
			return new ResponseEntity<Mono<City>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Mono<City>> insertCity(City city) {
		try {
			Mono<City> result = cityService.insert(city);
			return new ResponseEntity<Mono<City>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<City>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
