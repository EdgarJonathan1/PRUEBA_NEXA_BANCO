package bank.nexa.com.bankservice.transaction.Respository;

import bank.nexa.com.bankservice.transaction.model.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TypeProductRepository  extends JpaRepository<TypeProduct, Long> {
}
