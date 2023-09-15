package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;

import lombok.Data;

@Data
public class ExchangeRateData {
    private String record_date;
    private String country;
    private String exchange_rate;
}
