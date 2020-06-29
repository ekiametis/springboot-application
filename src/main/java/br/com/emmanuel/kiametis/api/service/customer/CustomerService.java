package br.com.emmanuel.kiametis.api.service.customer;

import com.mongodb.client.result.DeleteResult;

import br.com.emmanuel.kiametis.api.model.customer.Customer;
import br.com.emmanuel.kiametis.api.model.customer.CustomerUpdate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<Customer> insert(Customer customer);
	
	Mono<Customer> update(String identifier, CustomerUpdate customer);
	
	Mono<Customer> retrieve(String identifier);
	
	Mono<DeleteResult> delete(String identifier);
	
	Flux<Customer> list(String fullname, String identifier);
}
