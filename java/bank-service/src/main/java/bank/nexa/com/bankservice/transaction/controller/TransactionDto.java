package bank.nexa.com.bankservice.transaction.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionDto {

    @JsonProperty("numero_cuenta")
    private Integer numberAccount;
    @JsonProperty("monto")
    private Integer amount;
    @JsonProperty("operacion")
    private CodeOperation codeOperation;
    
}

