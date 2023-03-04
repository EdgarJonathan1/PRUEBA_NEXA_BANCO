package bank.nexa.com.bankservice.transaction.configuration;

import bank.nexa.com.bankservice.transaction.model.exception.ExceptionDto;
import bank.nexa.com.bankservice.transaction.model.exception.SupportedExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
public class RestExceptionInterceptor {

    @ExceptionHandler
    public ResponseEntity<ExceptionDto> processSupportedExceptions(Throwable throwable){
        loggError(throwable);
         return getSupportedException(throwable).orElseGet(this::responseEntityDefault);
    }

    private void loggError(Throwable throwable){
        System.err.println("Error: " + throwable.getMessage());
        throwable.printStackTrace();
    }

    private Optional<ResponseEntity<ExceptionDto>> getSupportedException(Throwable throwable){
        return Arrays.stream(SupportedExceptions.values())
                .filter(exception -> hasSameClass(exception, throwable))
                .map(exception -> createResponseEntity(exception, throwable))
                .findFirst();
    }
    private ResponseEntity<ExceptionDto> responseEntityDefault(){
        return  createResponseEntity(500,"500","Internal Server Error");
    }
    private Boolean hasSameClass(SupportedExceptions supportedException, Throwable throwable){
        return throwable.getClass().equals(supportedException.getExceptionClass());
    }

    private ResponseEntity<ExceptionDto> createResponseEntity(SupportedExceptions supportedException, Throwable throwable){
        return createResponseEntity(supportedException.getStatus().value(), supportedException.getCode(), throwable.getMessage());
    }

    private ResponseEntity<ExceptionDto> createResponseEntity(int status, String code, String message){

        return ResponseEntity
                .status(status)
                .body(ExceptionDto.builder()
                        .answerCode(code)
                        .answerDescription(message)
                        .idTransaction("no transaction")
                        .build()
                );
    }




}
