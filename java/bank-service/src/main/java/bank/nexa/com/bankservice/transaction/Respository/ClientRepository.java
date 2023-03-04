package bank.nexa.com.bankservice.transaction.Respository;

import bank.nexa.com.bankservice.transaction.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}


