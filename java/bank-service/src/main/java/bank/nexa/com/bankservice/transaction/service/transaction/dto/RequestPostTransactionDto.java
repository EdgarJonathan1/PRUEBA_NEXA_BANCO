package bank.nexa.com.bankservice.transaction.service.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestPostTransactionDto {

    @JsonProperty("numero_cuenta")
    private Integer numberAccount;
    @JsonProperty("monto")
    private Integer amount;
    @JsonProperty("operacion")
    private CodeOperation codeOperation;
    
}

