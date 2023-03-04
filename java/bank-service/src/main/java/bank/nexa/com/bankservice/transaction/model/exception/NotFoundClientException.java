package bank.nexa.com.bankservice.transaction.model.exception;

public class NotFoundClientException extends  RuntimeException{

    public NotFoundClientException(String message) {
        super(message);
    }

    public NotFoundClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public static NotFoundClientException of(Integer numberAccount){
        return new NotFoundClientException("El numero de cuenta: " + numberAccount + " no existe");
    }

}
