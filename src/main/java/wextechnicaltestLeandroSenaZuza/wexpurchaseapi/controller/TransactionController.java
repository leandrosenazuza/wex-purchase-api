package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.controller;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Transactions Controller")
@RequestMapping("${app.api.base}/")
public class TransactionController {

    /*
    @ApiOperation(value = "List all Transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success!"),
            @ApiResponse(code = 400, message = "Bad Request!"),
            @ApiResponse(code = 404, message = "Resource not found.")})
    @GetMapping(path = "/listAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllTransactions() {
        return ResponseEntity.ok().body();
    }*/

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
