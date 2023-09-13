package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class TransactionPurchaseRequest {
    @NotNull
    @ApiModelProperty(value = "Purchase Amount")
    private double purchaseAmount;
    private String description;
}
