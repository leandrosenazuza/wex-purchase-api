package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionPurchaseDTO {

    @ApiModelProperty(value = "Transaction's ID")
    private Long idTransaction;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Transaction's Date")
    private Date transactionDate;
    @ApiModelProperty(value = "Purchase Amount")
    private float purchaseAmount;
    @ApiModelProperty(value = "Description")
    private String description;
}
