package ucuenca.store.shoppingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ucuenca.store.shoppingservice.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}