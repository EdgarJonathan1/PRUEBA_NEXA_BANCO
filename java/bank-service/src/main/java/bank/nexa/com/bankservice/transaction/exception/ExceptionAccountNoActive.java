package bank.nexa.com.bankservice.transaction.exception;

public class ExceptionAccountNoActive  extends RuntimeException{

    public ExceptionAccountNoActive(String message) {
        super(message);
    }

    public ExceptionAccountNoActive(String message, Throwable cause) {
        super(message, cause);
    }
    
}
