package bank.nexa.com.bankservice.transaction.controller;

import bank.nexa.com.bankservice.transaction.Respository.AccountRepository;
import bank.nexa.com.bankservice.transaction.Respository.OperationCodeRepository;
import bank.nexa.com.bankservice.transaction.Respository.TransactionRepository;
import bank.nexa.com.bankservice.transaction.model.Account;
import bank.nexa.com.bankservice.transaction.model.OperationCode;
import bank.nexa.com.bankservice.transaction.model.Transaction;
import bank.nexa.com.bankservice.transaction.model.exception.AccountNoActiveException;
import bank.nexa.com.bankservice.transaction.model.exception.InsuficientFundException;
import bank.nexa.com.bankservice.transaction.model.exception.NotFoundClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OperationCodeRepository operationCodeRepository;

    @GetMapping("/{id}")
    public RequestGetTransaction test() {
        RequestGetTransaction requestGetTransaction = new RequestGetTransaction();
        requestGetTransaction.setCodClient(1);
        requestGetTransaction.setStartDate(LocalDate.now());
        requestGetTransaction.setEndDate(LocalDate.now());
        return requestGetTransaction;
    }

    @GetMapping
    public ResponseEntity<List<ResponseGetTransactionDto>> getTransaction(@RequestBody RequestGetTransaction requestGetTransaction) {
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
            ResponseGetTransactionDto responseGetTransactionDto = new ResponseGetTransactionDto();
            responseGetTransactionDto.setIdTransaction(transaction.getId());
            responseGetTransactionDto.setNumberAccount(transaction.getIdaccount().getId());
            responseGetTransactionDto.setClientName(getClientCompleteName(transaction.getIdaccount()));
            responseGetTransactionDto.setDateTransaction(transaction.getTransactionDate());
            responseGetTransactionDto.setAmount(transaction.getAmount());
            Optional<CodeOperation> codeOperation = getCodeOperation(transaction);
            codeOperation.ifPresent(responseGetTransactionDto::setCodeOperation);
            responseGetTransactionDto.setDescription(transaction.getDescription());

            responseGetTransactionDtos.add(responseGetTransactionDto);
        }

        return ResponseEntity.ok(responseGetTransactionDtos);
    }

    private String getClientCompleteName(Account account) {
        return account.getIdclient().getFirstName() + " " + account.getIdclient().getMiddleName() + " " + account.getIdclient().getLastName() + "" + account.getIdclient().getSecondLastName();
    }

    private Optional<CodeOperation> getCodeOperation(Transaction transaction) {
        return  Arrays.stream(CodeOperation.values())
                .filter(code -> code.name().equals(transaction.getIdoperationCode().getName()))
                .findFirst();
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createTransaction(@RequestBody TransactionDto transactionDto) {

        Optional<Account> account = accountRepository.findById(transactionDto.getNumberAccount());
        Optional<OperationCode> operationCode = operationCodeRepository.findByName(transactionDto.getCodeOperation().name());
        validateTransaction(account, transactionDto);


        Transaction transaction = new Transaction();
        if (account.isPresent() && operationCode.isPresent()) {
            transaction.setIdaccount(account.get());
            transaction.setIdoperationCode(operationCode.get());
            transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
            transaction.setTransactionDate(LocalDateTime.now());

            if (transactionDto.getCodeOperation().equals(CodeOperation.DEBITO)) {
                account.get().setBalance(account.get().getBalance().subtract(new BigDecimal(transactionDto.getAmount())));
            }

            if (transactionDto.getCodeOperation().equals(CodeOperation.CREDITO)) {
                account.get().setBalance(account.get().getBalance().add(new BigDecimal(transactionDto.getAmount())));
            }

            accountRepository.save(account.get());
            transaction = transactionRepository.save(transaction);

        }


        return ResponseEntity.ok(ResponseDto.builder()
                .answerCode("0")
                .answerDescription("OK")
                .idTransaction(transaction.getId().toString())
                .build());
    }

    private void validateTransaction(Optional<Account> account, TransactionDto transactionDto) {
        if (account.isPresent()) {
            validateAccountActive(account.get(), transactionDto);
            validateBalance(account.get(), transactionDto);
        }
    }

    private void validateAccountActive(Account account, TransactionDto transactionDto) {
        if (account.getIdstatus().getId().equals(2))
            throw AccountNoActiveException.of(transactionDto.getNumberAccount());
    }


    private void validateBalance(Account account, TransactionDto transactionDto) {
        BigDecimal balance = account.getBalance();
        BigDecimal amount = new BigDecimal(transactionDto.getAmount());
        if (balance.compareTo(amount) != 1)
            throw InsuficientFundException.of(transactionDto.getNumberAccount());
    }

}
