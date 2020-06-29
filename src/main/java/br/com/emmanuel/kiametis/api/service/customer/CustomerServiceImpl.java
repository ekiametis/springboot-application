package br.com.emmanuel.kiametis.api.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.DeleteResult;

import br.com.emmanuel.kiametis.api.error.ErrorType;
import br.com.emmanuel.kiametis.api.model.customer.Customer;
import br.com.emmanuel.kiametis.api.model.customer.CustomerUpdate;
import br.com.emmanuel.kiametis.api.repository.customer.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	public Mono<Customer> insert(Customer city) {
		Mono<Customer> insertedCity = customerRepository.save(city);
		return insertedCity;
	}

	public Mono<Customer> retrieve(String identifier) {
		Query query = new Query();
		query.addCriteria(Criteria.where("identifier").is(identifier));
		mongoTemplate.findOne(query, Customer.class);
		Mono<Customer> result = mongoTemplate.findOne(query, Customer.class)
				.switchIfEmpty(Mono.error(ErrorType.CUSTOMER_RETRIEVAL.getResponseException()
						.addDetailError(CustomerError.NOT_FOUND.getDetailErrorCode(),
								CustomerError.NOT_FOUND.getDetailErrorMessage())
						.addStatus(HttpStatus.NOT_FOUND.value())));
		return result;
	}

	public Flux<Customer> list(String fullname, String identifier) {
		Query query = new Query();
		if(fullname != null) {			
			query.addCriteria(Criteria.where("fullname").regex(fullname));
		}
		if(identifier != null) {			
			query.addCriteria(Criteria.where("identifier").is(identifier));
		}
		Flux<Customer> result = mongoTemplate.find(query, Customer.class);
		return result;
	}

	@Override
	public Mono<Customer> update(String identifier, CustomerUpdate customer) {
		Query query = new Query();
		query.addCriteria(Criteria.where("identifier").is(identifier));
		Update update = new Update();
		update.set("fullname", customer.getFullname());
		FindAndModifyOptions options = new FindAndModifyOptions();
	    options.returnNew(true);
		Mono<Customer> result = mongoTemplate.findAndModify(query, update, options, Customer.class)
				.switchIfEmpty(Mono.error(ErrorType.CUSTOMER_UPDATE.getResponseException()
						.addDetailError(CustomerError.NOT_FOUND.getDetailErrorCode(),
								CustomerError.NOT_FOUND.getDetailErrorMessage())
						.addStatus(HttpStatus.NOT_FOUND.value())));
		return result;
	}

	@Override
	public Mono<DeleteResult> delete(String identifier) {
		Query query = new Query();
		query.addCriteria(Criteria.where("identifier").is(identifier));
		Mono<DeleteResult> result = mongoTemplate.remove(query, Customer.class)
				.switchIfEmpty(Mono.error(ErrorType.CUSTOMER_DELETE.getResponseException()
						.addDetailError(CustomerError.NOT_FOUND.getDetailErrorCode(),
								CustomerError.NOT_FOUND.getDetailErrorMessage())
						.addStatus(HttpStatus.NOT_FOUND.value())));
		return result;
	}
}
