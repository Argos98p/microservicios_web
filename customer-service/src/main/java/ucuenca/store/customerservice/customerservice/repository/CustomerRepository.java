package ucuenca.store.customerservice.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucuenca.store.customerservice.customerservice.repository.entity.Customer;
import ucuenca.store.customerservice.customerservice.repository.entity.Region;

import java.util.List;



    public interface CustomerRepository  extends JpaRepository<Customer,Long> {
        public Customer findByNumberID(String numberID);
        public List<Customer> findByLastName(String lastName);
        public List<Customer> findByRegion(Region region);
    }

