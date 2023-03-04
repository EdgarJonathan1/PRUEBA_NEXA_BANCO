package bank.nexa.com.bankservice.transaction.model.exception;

import org.springframework.http.HttpStatus;

public enum SupportedExceptions {

    ACCOUNT_NO_ACTIVE(AccountNoActiveException.class, HttpStatus.BAD_REQUEST,"600"),
    INSUFICIENT_FUND(InsuficientFundException.class, HttpStatus.BAD_REQUEST,"601"),
    NOT_FOUND_CLIENT(NotFoundClientException.class, HttpStatus.BAD_REQUEST,"602"),
    ;


    private Class<? extends Throwable> exceptionClass;
    private HttpStatus status;
    private String code;

    private SupportedExceptions(Class<? extends Throwable>exception, HttpStatus status, String code) {
        this.exceptionClass = exception;
        this.status = status;
        this.code = code;
    }

    public Class<? extends Throwable> getExceptionClass() {
        return exceptionClass;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
