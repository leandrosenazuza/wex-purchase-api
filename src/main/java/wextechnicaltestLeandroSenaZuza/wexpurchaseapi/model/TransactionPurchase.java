package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TAB_TRANSACTION")
public class TransactionPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODG_TRANSACTION")
    private Long idTransaction;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date transactionDate;
    @Column(name = "PURCHASE_AMOUNT", nullable = false)
    private BigDecimal purchaseAmount;
    @Column(name = "DESCRIPTION", length = 50, nullable = true)
    private String description;

    public TransactionPurchase(Date transactionDate, BigDecimal purchaseAmount, String description) {
        this.transactionDate = transactionDate;
        this.purchaseAmount = purchaseAmount;
        this.description = description;
    }
}
