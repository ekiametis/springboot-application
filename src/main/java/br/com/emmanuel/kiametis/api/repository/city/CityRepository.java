package br.com.emmanuel.kiametis.api.repository.city;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.emmanuel.kiametis.api.model.city.City;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City, String>{}
