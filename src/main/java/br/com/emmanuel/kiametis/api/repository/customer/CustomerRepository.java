package br.com.emmanuel.kiametis.api.repository.customer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.com.emmanuel.kiametis.api.model.customer.Customer;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{

}
