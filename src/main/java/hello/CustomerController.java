package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	private CustomerRepository repository;

	public void setRepository(CustomerRepository repository) {this.repository = repository;}

	public CustomerController() {}
	
	@RequestMapping( method = {RequestMethod.GET})
	public Customer getCustomer(@RequestParam(value = "lastname") String lastname){
		return (Customer) repository.findByLastName(lastname).get(0);
	}
	
	@RequestMapping( method = {RequestMethod.POST} , consumes="application/json")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer){
		repository.save(newCustomer);
		return new ResponseEntity(newCustomer , HttpStatus.OK);
	}
}
