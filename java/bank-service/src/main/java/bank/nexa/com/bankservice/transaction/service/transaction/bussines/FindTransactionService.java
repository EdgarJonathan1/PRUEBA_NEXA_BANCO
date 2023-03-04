package bank.nexa.com.bankservice.transaction.service.transaction.bussines;

import bank.nexa.com.bankservice.transaction.Respository.database.bank.AccountRepository;
import bank.nexa.com.bankservice.transaction.Respository.database.bank.TransactionRepository;
import bank.nexa.com.bankservice.transaction.model.database.bank.Account;
import bank.nexa.com.bankservice.transaction.model.database.bank.Transaction;
import bank.nexa.com.bankservice.transaction.model.exception.NotFoundClientException;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.CodeOperation;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.RequestGetTransactionDto;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.ResponseGetTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class FindTransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;



    public List<ResponseGetTransactionDto> getTransaction(@RequestBody RequestGetTransactionDto requestGetTransaction) {
        Integer codClient = requestGetTransaction.getCodClient();
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalDateTime startDate = LocalDateTime.of(requestGetTransaction.getStartDate(), time);
        LocalDateTime endDate = LocalDateTime.of(requestGetTransaction.getEndDate(), time);

        Optional<Account> account = accountRepository.findById(codClient);
        List<Transaction> transactions = transactionRepository.findByTransactionDateBetweenAndIdaccount_Idclient_Id(startDate, endDate, codClient);

        if (!account.isPresent()) {
            throw NotFoundClientException.of(codClient);
        }

        List<ResponseGetTransactionDto> responseGetTransactionDtos = new LinkedList<>();

        for (Transaction transaction : transactions) {
            ResponseGetTransactionDto responseGetTransactionDto = createResponseGetTransactionDto(transaction);
            responseGetTransactionDtos.add(responseGetTransactionDto);
        }

        return responseGetTransactionDtos;

    }

    private ResponseGetTransactionDto createResponseGetTransactionDto(Transaction transaction) {
        ResponseGetTransactionDto responseGetTransactionDto = new ResponseGetTransactionDto();
        responseGetTransactionDto.setIdTransaction(transaction.getId());
        responseGetTransactionDto.setNumberAccount(transaction.getIdaccount().getId());
        responseGetTransactionDto.setClientName(getClientCompleteName(transaction.getIdaccount()));
        responseGetTransactionDto.setDateTransaction(transaction.getTransactionDate());
        responseGetTransactionDto.setAmount(transaction.getAmount());
        Optional<CodeOperation> codeOperation = getCodeOperation(transaction);
        codeOperation.ifPresent(responseGetTransactionDto::setCodeOperation);
        responseGetTransactionDto.setDescription(transaction.getDescription());

        return responseGetTransactionDto;
    }

    private String getClientCompleteName(Account account) {
        return account.getIdclient().getFirstName() + " " + account.getIdclient().getMiddleName() + " " + account.getIdclient().getLastName() + "" + account.getIdclient().getSecondLastName();
    }

    private Optional<CodeOperation> getCodeOperation(Transaction transaction) {
        return  Arrays.stream(CodeOperation.values())
                .filter(code -> code.name().equals(transaction.getIdoperationCode().getName()))
                .findFirst();
    }
}
