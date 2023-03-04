package bank.nexa.com.bankservice.transaction.service.account;

import bank.nexa.com.bankservice.transaction.Respository.database.bank.AccountRepository;
import bank.nexa.com.bankservice.transaction.model.database.bank.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/account-balance")
public class AccountBalanceController {

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping
    public ResponseEntity<List<ResponseAccountBalanceDto>> getAccountBalance() {

        List<Account> accounts = accountRepository.findAll();
        List<ResponseAccountBalanceDto> responseAccountBalanceDtos = new LinkedList<>();

        for (Account account:accounts ) {
            ResponseAccountBalanceDto responseAccountBalanceDto = new ResponseAccountBalanceDto();
            responseAccountBalanceDto.setAccountNumber(account.getId());
            responseAccountBalanceDto.setNameClient(account.getIdclient().getCompleteName());
            responseAccountBalanceDto.setTypeProduct(account.getIdproduct().getIdtypeAccount().getName());
            responseAccountBalanceDto.setInterestRate(account.getIdproduct().getInterestRate());
            responseAccountBalanceDto.setBalance(account.getBalance());
            responseAccountBalanceDto.setStatus(account.getIdstatus().getName());
            responseAccountBalanceDtos.add(responseAccountBalanceDto);
        }

        return ResponseEntity.ok(responseAccountBalanceDtos);
    }
}
