package bank.nexa.com.bankservice.transaction.Respository.database.bank;

import bank.nexa.com.bankservice.transaction.model.database.bank.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TypeProductRepository  extends JpaRepository<TypeProduct, Long> {
}
