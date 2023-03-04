package bank.nexa.com.bankservice.transaction.service;

import bank.nexa.com.bankservice.transaction.controller.TransactionDto;

public interface TransactionService {
    public void saveTransaction(TransactionDto transactionDto);
}
