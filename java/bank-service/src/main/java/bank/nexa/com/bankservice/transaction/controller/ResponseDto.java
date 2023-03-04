package bank.nexa.com.bankservice.transaction.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseDto {
   @JsonProperty("codigo_respuesta")
   private String answerCode;
   @JsonProperty("descripcion_respuesta")
   private String answerDescription;
   @JsonProperty("id_transaccion")
   private String idTransaction;
}
