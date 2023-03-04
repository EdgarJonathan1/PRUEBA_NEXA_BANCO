package bank.nexa.com.bankservice.transaction.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestGetTransaction {

    @JsonProperty("codigo_cliente")
    private Integer codClient;

    @JsonProperty("fecha_inicio")
    private LocalDate startDate;

    @JsonProperty("fecha_fin")
    private LocalDate endDate;

}
