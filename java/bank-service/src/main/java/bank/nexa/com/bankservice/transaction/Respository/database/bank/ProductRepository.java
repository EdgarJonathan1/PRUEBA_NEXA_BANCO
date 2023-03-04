package bank.nexa.com.bankservice.transaction.Respository.database.bank;

import bank.nexa.com.bankservice.transaction.model.database.bank.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
