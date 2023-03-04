package bank.nexa.com.bankservice.transaction.Respository;

import bank.nexa.com.bankservice.transaction.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
