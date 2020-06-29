package br.com.emmanuel.kiametis.api.controller.customer;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.result.DeleteResult;

import br.com.emmanuel.kiametis.api.model.customer.Customer;
import br.com.emmanuel.kiametis.api.model.customer.CustomerUpdate;
import br.com.emmanuel.kiametis.api.service.customer.CustomerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController implements CustomerControllerAPI {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	
	@Autowired
    private CustomerService customerService;

	@Autowired
	public CustomerController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<Mono<DeleteResult>> deleteCustomer(String identifier) {
		try {
			Mono<DeleteResult> result = customerService.delete(identifier);
			return new ResponseEntity<Mono<DeleteResult>>(result, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<DeleteResult>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Mono<Customer>> retrieveCustomer(String identifier) {
		try {
			Mono<Customer> result = customerService.retrieve(identifier);
			return new ResponseEntity<Mono<Customer>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Mono<Customer>> updateCustomer(CustomerUpdate customer, String identifier) {
		try {
			Mono<Customer> result = customerService.update(identifier, customer);
			return new ResponseEntity<Mono<Customer>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Flux<Customer>> listCustomers(String fullname, String identifier) {
		try {
			Flux<Customer> result = customerService.list(fullname, identifier);
			return new ResponseEntity<Flux<Customer>>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Flux<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Mono<Customer>> insertCustomer(Customer customer) {
		try {
			Mono<Customer> result = customerService.insert(customer);
			return new ResponseEntity<Mono<Customer>>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Some error occurs", e);
			return new ResponseEntity<Mono<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
