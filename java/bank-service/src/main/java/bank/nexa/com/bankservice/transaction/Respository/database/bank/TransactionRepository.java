package bank.nexa.com.bankservice.transaction.Respository.database.bank;

import bank.nexa.com.bankservice.transaction.model.database.bank.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.transactionDate between ?1 and ?2 and t.idaccount.idclient.id = ?3")
    List<Transaction> findByTransactionDateBetweenAndIdaccount_Idclient_Id(LocalDateTime transactionDateStart, LocalDateTime transactionDateEnd, Integer id);
}
