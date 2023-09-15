package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeRateResponse {
    private List<ExchangeRateData> data;
    private MetaData meta;
    private Links links;
}