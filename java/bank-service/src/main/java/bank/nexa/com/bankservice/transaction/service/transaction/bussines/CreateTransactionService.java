package bank.nexa.com.bankservice.transaction.service.transaction.bussines;

import bank.nexa.com.bankservice.transaction.Respository.database.bank.AccountRepository;
import bank.nexa.com.bankservice.transaction.Respository.database.bank.OperationCodeRepository;
import bank.nexa.com.bankservice.transaction.Respository.database.bank.TransactionRepository;
import bank.nexa.com.bankservice.transaction.model.database.bank.Account;
import bank.nexa.com.bankservice.transaction.model.database.bank.OperationCode;
import bank.nexa.com.bankservice.transaction.model.database.bank.Transaction;
import bank.nexa.com.bankservice.transaction.model.exception.AccountNoActiveException;
import bank.nexa.com.bankservice.transaction.model.exception.InsuficientFundException;
import bank.nexa.com.bankservice.transaction.model.exception.NotFoundClientException;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.CodeOperation;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.RequestPostTransactionDto;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.ResponsePostTransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class CreateTransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OperationCodeRepository operationCodeRepository;

    public ResponsePostTransactionDto createTransaction(@RequestBody RequestPostTransactionDto transactionDto) {
        Optional<Account> account = accountRepository.findById(transactionDto.getNumberAccount());
        Optional<OperationCode> operationCode = operationCodeRepository.findByName(transactionDto.getCodeOperation().name());

        validateTransaction(account, transactionDto);

        Transaction transaction = new Transaction();
        if (account.isPresent() && operationCode.isPresent()) {
            transaction = createTransaction(account.get(), operationCode.get(), transactionDto);
            if (isDebit(transactionDto))
                applyDebit(transactionDto, account.get());

            if (isCredit(transactionDto))
                applyCredit(transactionDto, account.get());
            accountRepository.save(account.get());
            transaction = transactionRepository.save(transaction);
        }

        return buildResponse(transaction.getId().toString());
    }

    private void validateTransaction(Optional<Account> account, RequestPostTransactionDto transactionDto) {
        if (account.isPresent()) {
            validateAccountActive(account.get(), transactionDto);
            if(isDebit(transactionDto))
                validateBalance(account.get(), transactionDto);
        }else{
            throw NotFoundClientException.of(transactionDto.getNumberAccount());
        }
    }

    private Transaction createTransaction(Account account, OperationCode operationCode, RequestPostTransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setIdaccount(account);
        transaction.setIdoperationCode(operationCode);
        transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
        transaction.setTransactionDate(LocalDateTime.now());
        return transaction;
    }

    private boolean isDebit(RequestPostTransactionDto transactionDto) {
        return transactionDto.getCodeOperation().equals(CodeOperation.DEBITO);
    }
    private boolean isCredit(RequestPostTransactionDto transactionDto) {
        return transactionDto.getCodeOperation().equals(CodeOperation.CREDITO);
    }
    private void applyDebit(RequestPostTransactionDto transactionDto, Account account) {
        account.setBalance(account.getBalance().subtract(new BigDecimal(transactionDto.getAmount())));
    }

    private void applyCredit(RequestPostTransactionDto transactionDto, Account account) {
        account.setBalance(account.getBalance().add(new BigDecimal(transactionDto.getAmount())));
    }


    private void validateAccountActive(Account account, RequestPostTransactionDto transactionDto) {
        if (account.getIdstatus().getId().equals(2))
            throw AccountNoActiveException.of(transactionDto.getNumberAccount());
    }


    private void validateBalance(Account account, RequestPostTransactionDto transactionDto) {
        BigDecimal balance = account.getBalance();
        BigDecimal amountDebit = new BigDecimal(transactionDto.getAmount());
        if (hasMoreBalanceThatAmount(balance, amountDebit))
            throw InsuficientFundException.of(transactionDto.getNumberAccount());
    }

    private boolean hasMoreBalanceThatAmount(BigDecimal balance, BigDecimal amountDebit) {
        return balance.compareTo(amountDebit) != 1;
    }

    private ResponsePostTransactionDto buildResponse(String  idTransaction) {
        return ResponsePostTransactionDto.builder()
                .answerCode("0")
                .answerDescription("OK")
                .idTransaction(idTransaction)
                .build();
    }
}
