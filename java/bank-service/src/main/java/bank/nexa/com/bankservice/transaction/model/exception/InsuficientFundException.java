package bank.nexa.com.bankservice.transaction.model.exception;

public class InsuficientFundException extends RuntimeException{
    

    public InsuficientFundException(String message) {
        super(message);
    }

    public InsuficientFundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InsuficientFundException of(Integer numberAccount){
        return new InsuficientFundException("The account number: " + numberAccount + " has insuficient fund");
    }

}
