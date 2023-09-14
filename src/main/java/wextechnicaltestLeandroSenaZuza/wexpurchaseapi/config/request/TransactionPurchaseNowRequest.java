package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionPurchaseNowRequest {
    @NotNull
    @ApiModelProperty(value = "Purchase Amount")
    private BigDecimal purchaseAmount;
    private String description;
}
