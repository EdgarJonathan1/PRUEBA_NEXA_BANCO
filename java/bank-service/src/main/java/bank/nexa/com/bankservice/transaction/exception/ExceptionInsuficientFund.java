package bank.nexa.com.bankservice.transaction.exception;

public class ExceptionInsuficientFund  extends RuntimeException{
    

    public ExceptionInsuficientFund(String message) {
        super(message);
    }

    public ExceptionInsuficientFund(String message, Throwable cause) {
        super(message, cause);
    }

}
