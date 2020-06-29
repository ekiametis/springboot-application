package br.com.emmanuel.kiametis.api.service.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.emmanuel.kiametis.api.error.ErrorType;
import br.com.emmanuel.kiametis.api.model.city.City;
import br.com.emmanuel.kiametis.api.model.city.State;
import br.com.emmanuel.kiametis.api.repository.city.CityRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Autowired
	private CityRepository cityRepository;

	public Mono<City> insert(City city) {
		Mono<City> insertedCity = cityRepository.save(city);
		return insertedCity;
	}

	public Mono<City> retrieve(String id) {
		Mono<City> city = cityRepository.findById(id);
		return city.switchIfEmpty(
				Mono.error(
						ErrorType.CITY_RETRIEVAL.
							getResponseException().
							addDetailError(
									CityError.NOT_FOUND.getDetailErrorCode(),
									CityError.NOT_FOUND.getDetailErrorMessage()
							).addStatus(HttpStatus.NOT_FOUND.value())
				)
		);
	}
	
	public Flux<City> list(String name, State state) {
		Query query = new Query();
		if(name != null) {			
			query.addCriteria(Criteria.where("name").regex(name));
		}
		if(state != null) {			
			query.addCriteria(Criteria.where("state").is(state));
		}
		Flux<City> result = mongoTemplate.find(query, City.class);
		return result;
	}
}
