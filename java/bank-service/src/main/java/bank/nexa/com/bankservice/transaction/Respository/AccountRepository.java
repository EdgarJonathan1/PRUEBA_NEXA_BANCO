package bank.nexa.com.bankservice.transaction.Respository;

import bank.nexa.com.bankservice.transaction.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
