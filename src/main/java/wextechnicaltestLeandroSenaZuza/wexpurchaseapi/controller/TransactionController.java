package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service.TransactionService;

import java.util.List;

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
    public ResponseEntity<?> findById(@ApiParam(value = "Transaction ID", required = true) @PathVariable(value = "idPurchase") Long idPurchase) throws Exception {
       TransactionPurchaseDTO transactionDTO = this.transactionMapper.toDTO(transactionService.findById(idPurchase));
       return ResponseEntity.ok(transactionDTO);
    }

    @ApiOperation(value = "Get All Transactions withou filter.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @GetMapping(path = "/listAllPurchase", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByTransactions() throws Exception {
        List<TransactionPurchaseDTO> transactionDTOList = this.transactionMapper.toListDTO(transactionService.findAll());
        return ResponseEntity.ok(transactionDTOList);
    }

    @ApiOperation(value = "Create one purchase in the Database.", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createPurchase(@RequestBody TransactionPurchaseRequest request) throws Exception {
        return ResponseEntity.ok(this.transactionMapper.toDTO(transactionService.createPurchase(request)));
    }
}
