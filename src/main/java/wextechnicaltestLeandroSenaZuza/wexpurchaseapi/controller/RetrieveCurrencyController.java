package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.DecimalFormatException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.ConversionRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ConversionResponse;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.ExchangeRateResponse;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service.ExternalFiscalService;

@RestController
public class RetrieveCurrencyController {

    @Autowired
    public ExternalFiscalService externalFiscalService;

    @ApiOperation(value = "Get One Transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @GetMapping(path = "/getOnePurchase/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOneApiFiscal() throws Exception {
        //
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get One Transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @PostMapping(path = "/getPurchaseWithCurrency/{idTransaction}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConversionResponse> getConvertedTransaction(@PathVariable Long idTransaction,
                                                                      @RequestBody ConversionRequest request) throws Exception, DecimalFormatException {
        return ResponseEntity.ok(externalFiscalService.getConvertedCash(idTransaction, request));
    }
}
