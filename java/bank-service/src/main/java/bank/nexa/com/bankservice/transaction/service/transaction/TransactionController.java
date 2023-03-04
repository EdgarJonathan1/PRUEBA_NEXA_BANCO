package bank.nexa.com.bankservice.transaction.service.transaction;

import bank.nexa.com.bankservice.transaction.service.transaction.bussines.CreateTransactionService;
import bank.nexa.com.bankservice.transaction.service.transaction.bussines.FindTransactionService;
import bank.nexa.com.bankservice.transaction.service.transaction.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private FindTransactionService findTransactionService;


    @Autowired
    private CreateTransactionService createTransactionService;

    @GetMapping
    public ResponseEntity<List<ResponseGetTransactionDto>> getTransaction(@RequestBody RequestGetTransactionDto requestGetTransaction) {
        return ResponseEntity.ok(findTransactionService.getTransaction(requestGetTransaction));
    }


    @PostMapping
    public ResponseEntity<ResponsePostTransactionDto> createTransaction(@RequestBody RequestPostTransactionDto transactionDto) {
        return ResponseEntity.ok(createTransactionService.createTransaction(transactionDto));
    }


}
