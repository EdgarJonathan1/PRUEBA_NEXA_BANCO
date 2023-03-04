package bank.nexa.com.bankservice.transaction.Respository.database.bank;

import bank.nexa.com.bankservice.transaction.model.database.bank.OperationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationCodeRepository extends JpaRepository<OperationCode, Long> {
    Optional<OperationCode> findByName(String name);
}
