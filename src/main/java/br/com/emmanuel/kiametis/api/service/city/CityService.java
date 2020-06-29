package br.com.emmanuel.kiametis.api.service.city;

import br.com.emmanuel.kiametis.api.model.city.City;
import br.com.emmanuel.kiametis.api.model.city.State;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {

	Mono<City> insert(City city);
	
	Mono<City> retrieve(String id);
	
	Flux<City> list(String name, State state);
}
