package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRateResponseWrapper {
    private List<ExchangeRateData> data;
    private MetaData meta;
    private Links links;

}
