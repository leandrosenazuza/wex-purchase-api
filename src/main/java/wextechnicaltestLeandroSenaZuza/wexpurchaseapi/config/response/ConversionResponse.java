package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConversionResponse {
    private Long idTransaction;
    private String description;
    private Date transactionDate;
    private double purchaseAmount;
    private String exchange_rate;
    private String country;
    private String convertedAmount;
}