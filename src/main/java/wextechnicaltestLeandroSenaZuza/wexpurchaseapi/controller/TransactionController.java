package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Transactions Controller")
@RequestMapping("${app.api.base}/")
public class TransactionController {
}
