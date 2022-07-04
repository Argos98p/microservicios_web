package ucuenca.store.shoppingservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ucuenca.store.shoppingservice.model.Product;

@FeignClient(name = "product-service", path = "/products")
public interface ProductClient {
    @GetMapping(value = "/{id}")
    @CircuitBreaker(name="productDB",fallbackMethod = "getProductFallback")
    public ResponseEntity<Product> get_Product(@PathVariable("id") Long id);
    @CircuitBreaker(name = "productUpdateDB",fallbackMethod = "getProductFallback")
    @GetMapping(value = "/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable  Long id ,@RequestParam(name = "quantity", required = true) Double quantity);

    default ResponseEntity<Product> getProductFallback(RuntimeException e){
        Product product = Product.builder()
                .id(0L)
                .name("none")
                .description("none")
                .price(0.0)
                .stock(0.0)
                .build();
        return ResponseEntity.ok(product);
    }
}


