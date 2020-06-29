package br.com.emmanuel.kiametis.api.controller.customer;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.client.result.DeleteResult;

import br.com.emmanuel.kiametis.api.error.ResponseException;
import br.com.emmanuel.kiametis.api.model.customer.Customer;
import br.com.emmanuel.kiametis.api.model.customer.CustomerUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Api(value = "customers")
public interface CustomerControllerAPI {

	@ApiOperation(value = "Delete customer", nickname = "deleteCustomer", notes = "This resource remove a customer by the customer's identifier", tags = {
			"customer-controller", })
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Customer removed with success."),
			@ApiResponse(code = 404, message = "Customer not found.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/customers/{identifier}", produces = { "application/json" }, method = RequestMethod.DELETE)
	ResponseEntity<Mono<DeleteResult>> deleteCustomer(
			@Pattern(regexp = "^\\d{11}$") @ApiParam(value = "Customer identifier", required = true) @PathVariable("identifier") String identifier);

	@ApiOperation(value = "Find a customer", nickname = "retrieveCustomer", notes = "This resource retrieve a customer by the customer's identifier.", response = Customer.class, tags = {
			"customer-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer retrieved with success.", response = Customer.class),
			@ApiResponse(code = 404, message = "Customer not found.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal Server Error.", response = ResponseException.class) })
	@RequestMapping(value = "/customers/{identifier}", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Mono<Customer>> retrieveCustomer(
			@Pattern(regexp = "^\\d{11}$") @ApiParam(value = "Customer identifier", required = true) @PathVariable("identifier") String identifier);

	@ApiOperation(value = "Update a customer", nickname = "updateCustomer", notes = "This resource update a customer by the customer's identifier.", response = Customer.class, tags = {
			"customer-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customer updated with success.", response = Customer.class),
			@ApiResponse(code = 404, message = "Customer not found.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/customers/{identifier}", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PATCH)
	ResponseEntity<Mono<Customer>> updateCustomer(
			@ApiParam(value = "Propriedades de alteração de cliente", required = true) @Valid @RequestBody CustomerUpdate customer,
			@Pattern(regexp = "^\\d{11}$") @ApiParam(value = "Customer identifier", required = true) @PathVariable("identifier") String identifier);

	@ApiOperation(value = "List Customers", nickname = "listCustomers", notes = "This resource list customers based in parameters.", response = Customer.class, tags = {
			"customer-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Customers listed.", response = Customer.class, responseContainer = "List"),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/customers", produces = { "application/json" }, method = RequestMethod.GET)
	ResponseEntity<Flux<Customer>> listCustomers(
			@ApiParam(value = "Customer fullname") @Valid @RequestParam(value = "fullname", required = false) String fullname,
			@Pattern(regexp = "^\\d{11}$") @ApiParam(value = "Customer identifier") @Valid @RequestParam(value = "identifier", required = false) String identifier);

	@ApiOperation(value = "Insert a customer", nickname = "insertCustomer", notes = "This resource insert a customer", response = Customer.class, tags = {
			"customer-controller", })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Insert a customer.", response = Customer.class),
			@ApiResponse(code = 400, message = "Bussiness error.", response = ResponseException.class),
			@ApiResponse(code = 500, message = "Internal server error.", response = ResponseException.class) })
	@RequestMapping(value = "/customers", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<Mono<Customer>> insertCustomer(
			@ApiParam(value = "Customer properties", required = true) @Valid @RequestBody Customer customer);

}
