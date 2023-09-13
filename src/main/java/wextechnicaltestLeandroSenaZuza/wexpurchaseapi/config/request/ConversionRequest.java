package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/*The retrieved purchase should include the identifier ok, the description, the transaction date, the original US dollar purchase
        amount, the exchange rate used, and the converted amount based upon the specified currency’s exchange rate for the
        date of the purchase.*/

@Data
public class ConversionRequest {
    private String country;
    //private String currency;
}
