package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service.TransactionService;

@RestController
@Api(tags = "Transactions Controller")
@RequestMapping("${app.api.base}/transactions/")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    TransactionMapper transactionMapper;

   @ApiOperation(value = "Get One Transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @GetMapping(path = "/getOnePurchase/{idPurchase}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable(value = "idPurchase") Long idPurchase) throws Exception {
       TransactionPurchaseDTO transactionDTO = this.transactionMapper.toDTO(transactionService.findById(idPurchase));
       return ResponseEntity.ok(transactionDTO);
    }

    /*@ApiOperation(value = "Get One Transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @GetMapping(path = "/getOne/{idTransaction}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTransactions() {

        return ResponseEntity.ok().body();
    }*/
}
