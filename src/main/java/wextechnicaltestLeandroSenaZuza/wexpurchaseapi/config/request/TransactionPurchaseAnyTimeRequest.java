package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionPurchaseAnyTimeRequest {
    @NotNull
    @ApiModelProperty(value = "Purchase Amount")
    private BigDecimal purchaseAmount;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String transactionDate;
    private String description;
}
