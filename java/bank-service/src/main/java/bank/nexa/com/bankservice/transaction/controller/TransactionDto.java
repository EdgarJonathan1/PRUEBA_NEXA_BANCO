package bank.nexa.com.bankservice.transaction.controller;

import lombok.Data;
import java.util.Date;

@Data
public class TransactionDto {

    private Long numberAccount;
    private Date dateTransaction;
    private Long amount;
    private CodeOperation codeOperation;
    
}

enum CodeOperation { DEBIT, CREDIT }

