package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionPurchaseRequest {
    @NotNull
    @ApiModelProperty(value = "Purchase Amount")
    private BigDecimal purchaseAmount;
    @NotNull
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String transactionDate;
    private String description;
}
