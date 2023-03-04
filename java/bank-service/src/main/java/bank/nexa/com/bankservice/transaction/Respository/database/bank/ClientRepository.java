package bank.nexa.com.bankservice.transaction.Respository.database.bank;

import bank.nexa.com.bankservice.transaction.model.database.bank.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}


