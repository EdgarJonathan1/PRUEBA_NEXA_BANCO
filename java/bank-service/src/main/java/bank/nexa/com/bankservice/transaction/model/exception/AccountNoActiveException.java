package bank.nexa.com.bankservice.transaction.model.exception;

public class AccountNoActiveException extends RuntimeException{

    public AccountNoActiveException(String message) {
        super(message);
    }

    public AccountNoActiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public static AccountNoActiveException of(Integer numberAccount){
        return new AccountNoActiveException("The account number: " + numberAccount + " is not active");
    }
    
}
