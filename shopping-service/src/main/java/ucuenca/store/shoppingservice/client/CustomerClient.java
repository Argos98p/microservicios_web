package ucuenca.store.shoppingservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ucuenca.store.shoppingservice.model.Customer;


@FeignClient(name = "customer-service", path = "/customers")
public interface CustomerClient {
    @GetMapping(value = "/{id}")
    @CircuitBreaker(name="customerDB",fallbackMethod="getCustomerFallback")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id);
    default ResponseEntity<Customer> getCustomerFallback(RuntimeException e){
        Customer customer = Customer.builder()
                .firstName("nonde")
                .lastName("none")
                .email("noen")
                .photoUrl("none").build();
        //return new ResponseEntity<String>("subscribe service is down", HttpStatus.OK);
        return ResponseEntity.ok(customer);
    }
}
