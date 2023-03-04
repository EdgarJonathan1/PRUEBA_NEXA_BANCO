package bank.nexa.com.bankservice.transaction.service.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ResponseGetTransactionDto {
    @JsonProperty("id_transaction")
    private Integer idTransaction;

    @JsonProperty("numero_cuenta")
    private Integer numberAccount;

    @JsonProperty("nombre_cliente")
    private String clientName;

    @JsonProperty("fecha_hora")
    private LocalDateTime dateTransaction;

    @JsonProperty("monto")
    private BigDecimal amount;

    @JsonProperty("operacion")
    private CodeOperation codeOperation;

    @JsonProperty("descripcion")
    private String description;


}
