package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Data
public class TransactionPurchaseRequest {

    @NotNull
    @DecimalMax(value = "10000", inclusive = false, message = "Purchase amount must be less than 10,000.00")
    @ApiModelProperty(value = "Purchase Amount")
    private BigDecimal purchaseAmount;
    @NotBlank(message = "Transaction date must not be blank!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String transactionDate;
    @Size(max = 50, message = "Description must be at most 50 characters")
    private String description;
}
