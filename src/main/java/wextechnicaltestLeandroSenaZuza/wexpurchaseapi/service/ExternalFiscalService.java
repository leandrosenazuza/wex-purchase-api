package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.BadParamRequestException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.NotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.ConversionRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ConversionResponse;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ExchangeRateData;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.DecimalFormatException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ExchangeRateResponseWrapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<ExchangeRateData> requestFiscalAPI(String country, String date){
        try {
            String entityToBeJSON = sendHttpRequest(country, date);
            System.out.print(entityToBeJSON);

            ExchangeRateResponseWrapper responseWrapper = gson.fromJson(entityToBeJSON, ExchangeRateResponseWrapper.class);

            List<ExchangeRateData> responseList = responseWrapper.getData();

            return responseList;
        } catch (Exception e) {
            throw new BadParamRequestException("Internal Error.");
        }
    }

    private ConversionResponse getExchangeTransaction(ConversionRequest request, TransactionPurchaseDTO transactionDTO) throws DecimalFormatException {
        List<ExchangeRateData> responseList = requestFiscalAPI(request.getCountry(), transactionDTO.getTransactionDate());

        ConversionResponse responseConverted = getConversionResponse(request, transactionDTO, responseList);

        return responseConverted;
    }

    private ConversionResponse getConversionResponse(ConversionRequest request, TransactionPurchaseDTO transactionDTO, List<ExchangeRateData> responseList) throws DecimalFormatException {
        ConversionResponse responseConverted = new ConversionResponse();
        responseConverted.setIdTransaction(transactionDTO.getIdTransaction());
        responseConverted.setDescription(transactionDTO.getDescription());
        responseConverted.setPurchaseAmount(transactionDTO.getPurchaseAmount());
        responseConverted.setTransactionDate(transactionDTO.getTransactionDate());
        responseConverted.setCountry(request.getCountry());

        String exchangeRate = getExchangeRateByRecordDate(responseList, transactionDTO.getTransactionDate());

        if (exchangeRate != null) {
            responseConverted.setExchange_rate(toSpecifyAnExchangeRate(exchangeRate));
            responseConverted.setConvertedAmount(convertAmountByExchangeRate(transactionDTO.getPurchaseAmount(), exchangeRate));
        } else {
            throw new NotFoundException("There is no exchange rate data available or the name of the country is incorrect.");
        }
        return responseConverted;
    }


    public String sendHttpRequest(String country, String date) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        String urlRequest = getUrlBuilder(country, date);
        HttpGet httpGet = new HttpGet();
        URIBuilder builder = new URIBuilder(urlRequest);
        httpGet.setURI(builder.build());

        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity.getContent() != null) {
                return EntityUtils.toString(httpEntity);
            } else {
                throw new Exception("Not found any exchange");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public String getUrlBuilder(String country, String date){
        String lastSixMonth = getLastSixMonths(date);
        return URL
                + "?fields=country,record_date,exchange_rate&filter=record_date:gte:" + lastSixMonth +",record_date:lte:"+ date +",country:in:("+ country +")&sort=-record_date";
    }

    private String getLastSixMonths(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        try {
            Date data = sdf.parse(date);
            calendar.setTime(data);
            calendar.add(Calendar.MONTH, -6);
            String formatedDate = sdf.format(calendar.getTime());
            return formatedDate.toString();
        } catch (ParseException e) {
            throw new BadParamRequestException("Error to convert Date");
        }
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


    private String getExchangeRateByRecordDate(List<ExchangeRateData> exchangeRateResponseList, String targetRecordDate) {
        String exchangeRate = null;
        boolean notFoundExactDate = false;
        for (ExchangeRateData exchangeRateData : exchangeRateResponseList) {
            if (targetRecordDate.equals(exchangeRateData.getRecord_date())) {
                exchangeRate = exchangeRateData.getExchange_rate();
                return exchangeRate;
            } else if (notFoundExactDate && (exchangeRateData.getRecord_date() != null)) {
                exchangeRate = exchangeRateData.getExchange_rate();
                return exchangeRate;
            }
            notFoundExactDate = true;
        }
        return null;

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
