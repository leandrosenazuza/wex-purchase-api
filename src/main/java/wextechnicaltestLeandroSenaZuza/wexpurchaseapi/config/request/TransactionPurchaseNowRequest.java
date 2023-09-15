package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class TransactionPurchaseNowRequest {
    @NotNull
    @ApiModelProperty(value = "Purchase Amount")
    private BigDecimal purchaseAmount;
    private String description;
}
