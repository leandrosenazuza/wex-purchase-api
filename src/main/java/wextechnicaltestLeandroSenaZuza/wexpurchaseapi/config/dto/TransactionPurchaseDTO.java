package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionPurchaseDTO {

    @ApiModelProperty(value = "Transaction's ID")
    private Long idTransaction;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Transaction's Date")
    private Date transactionDate;
    @ApiModelProperty(value = "Purchase Amount")
    private double purchaseAmount;
    @ApiModelProperty(value = "Description")
    private String description;
}
