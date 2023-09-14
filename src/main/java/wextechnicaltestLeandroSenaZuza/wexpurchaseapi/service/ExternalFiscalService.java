package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.DecimalFormatException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//TODO ● Ao converter entre moedas, você não precisa de uma correspondência exata de datas, mas deve usar uma taxa de conversão de moeda inferior ou igual à data da compra nos últimos 6 meses.
//TODO ● Se não houver taxa de conversão de moeda disponível dentro de 6 meses igual ou antes da data da compra, um erro deve ser retornado informando que a compra não pode ser convertida para a moeda de destino.
//TODO ● O valor convertido da compra para a moeda de destino deve ser arredondado para duas casas decimais (ou seja, centavos).



@Service
public class ExternalFiscalService {

    @Autowired
    TransactionService transactionService;
    @Autowired
    private TransactionMapper transactionMapper;

    @Value("${app.api.url}")
    private String URL;

    Gson gson = new Gson();




    public ExchangeRateResponse requestFiscalAPI(String country, String date) throws Exception {
        try{
            ExchangeRateResponse response =  gson.fromJson(sendHttpRequest(country, date), ExchangeRateResponse.class);
            return response;
        }catch (Exception e){
            throw new ConvertException("Error to convert JSON.");
        }

    }

    public String sendHttpRequest(String country, String date) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrlBuilder(country, date));

        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity.getContent() != null) {
                return EntityUtils.toString(httpEntity);
            } else {
                throw new Exception("Not found any exange");
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

    public ConversionResponse getConvertedCash(Long idTransaction, ConversionRequest request) throws Exception, DecimalFormatException {
        TransactionPurchaseDTO transactionDTO = convertTransactionToDTO(transactionService.findById(idTransaction));

        ConversionResponse response = getExchangeTransaction(request, transactionDTO);
        return response;
    }

    private TransactionPurchaseDTO convertTransactionToDTO(TransactionPurchase transaction) {
        TransactionPurchaseDTO dto = new TransactionPurchaseDTO();
        dto.setIdTransaction(transaction.getIdTransaction());
        dto.setDescription(transaction.getDescription());
        dto.setTransactionDate(formatPurchaseDate(transaction.getTransactionDate()));
        dto.setPurchaseAmount(transaction.getPurchaseAmount());

        return dto;
    }

    private String formatPurchaseDate(Date transactionDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(transactionDate);

        return formattedDate;
    }

    private ConversionResponse getExchangeTransaction(ConversionRequest request, TransactionPurchaseDTO transactionDTO) throws Exception, DecimalFormatException {
        ExchangeRateResponse exchangeRateResponse = requestFiscalAPI(request.getCountry(), transactionDTO.getTransactionDate());
        ConversionResponse responseConverted = new ConversionResponse();
        responseConverted.setIdTransaction(transactionDTO.getIdTransaction());
        responseConverted.setDescription(transactionDTO.getDescription());
        responseConverted.setPurchaseAmount(transactionDTO.getPurchaseAmount());
        responseConverted.setTransactionDate(transactionDTO.getTransactionDate());
        responseConverted.setCountry(request.getCountry());

        if(exchangeRateResponse.getData() != null){
            responseConverted.setExchange_rate(toSpecifyAnExchangeRate(exchangeRateResponse.getData().get(0).getExchange_rate()));
        }

        responseConverted.setConvertedAmount(convertAmountByExchangeRate(transactionDTO.getPurchaseAmount(), exchangeRateResponse.getData().get(0).getExchange_rate()));

        return responseConverted;
    }

    private String toSpecifyAnExchangeRate(String data) throws DecimalFormatException {
        System.out.print(data);
        try {

            double number = Double.parseDouble(data);

            DecimalFormat df = new DecimalFormat("#.00");
            String formattedNumber = df.format(number);

            return formattedNumber;
        } catch (NumberFormatException e) {
            throw new DecimalFormatException("Erro to convert do decimal with to places.");
        }
    }



    private String convertAmountByExchangeRate(BigDecimal purchaseAmount, String rate) {
        BigDecimal rateNumber = BigDecimal.valueOf(Double.valueOf(rate));
        BigDecimal amountWithRate = rateNumber.multiply(purchaseAmount).setScale(2, RoundingMode.HALF_EVEN);
        return  amountWithRate.toString();
    }
}
