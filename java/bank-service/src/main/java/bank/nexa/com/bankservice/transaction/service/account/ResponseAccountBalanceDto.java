package bank.nexa.com.bankservice.transaction.service.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseAccountBalanceDto {
    @JsonProperty("numero_cuenta")
    private Integer accountNumber;
    @JsonProperty("nombre_cliente")
    private String nameClient;

    @JsonProperty("tipo_cuenta")
    private String typeProduct;
    @JsonProperty("tasa_interes")
    private BigDecimal interestRate;
    @JsonProperty("saldo")
    private BigDecimal balance;

    @JsonProperty("estatus")
    private String status;

}
