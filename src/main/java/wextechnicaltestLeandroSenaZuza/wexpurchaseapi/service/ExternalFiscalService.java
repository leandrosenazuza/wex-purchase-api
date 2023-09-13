package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.transaction.Transaction;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.ConvertException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.ConversionRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ConversionResponse;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ExchangeRateResponse;

import java.net.http.HttpHeaders;
import java.util.Date;


@Service
public class ExternalFiscalService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    private TransactionMapper transactionMapper;

    @Value("${app.api.url}")
    private String URL;

    Gson gson = new Gson();




    public ExchangeRateResponse requestFiscalAPI() throws Exception {
        try{
            ExchangeRateResponse response =  gson.fromJson(sendHttpRequest(), ExchangeRateResponse.class);
            return response;
        }catch (Exception e){
            throw new ConvertException("Error to convert JSON.");
        }

    }

    public String sendHttpRequest() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrlBuilder("Canada", "2023-06-30"));

        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                return EntityUtils.toString(httpEntity);
            } else {
                throw new Exception("Empty response body.");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String getUrlBuilder(String country, String date){
        return URL
                + "?fields="
                + getSearchFields("exchange_rate")
                + "&filter="
                + getFilter(country, date);
    }

    public String getSearchFields(String field){
        return field;
    }
    public String getFilter(String country, String date){
        return "country:in:("+country+"),record_date:eq:"+date;
    }

    public ConversionResponse getConvertedCash(Long idTransaction, ConversionRequest request) throws Exception {
        TransactionPurchaseDTO transactionDTO = transactionMapper.toDTO(transactionService.findById(idTransaction));

        ConversionResponse response = getExchangeTransaction(request, transactionDTO);
        return response;
    }

    private ConversionResponse getExchangeTransaction(ConversionRequest request, TransactionPurchaseDTO transactionDTO) throws Exception {
        ExchangeRateResponse exchangeRateResponse = requestFiscalAPI();
        ConversionResponse responseConverted = new ConversionResponse();
        responseConverted.setIdTransaction(transactionDTO.getIdTransaction());
        responseConverted.setDescription(transactionDTO.getDescription());
        responseConverted.setPurchaseAmount(transactionDTO.getPurchaseAmount());
        responseConverted.setTransactionDate(transactionDTO.getTransactionDate());
        responseConverted.setCountry(request.getCountry());
        if(exchangeRateResponse.getData() != null){
            responseConverted.setExchange_rate(exchangeRateResponse.getData().get(0).getExchange_rate());
        }

        return responseConverted;
    }
}
