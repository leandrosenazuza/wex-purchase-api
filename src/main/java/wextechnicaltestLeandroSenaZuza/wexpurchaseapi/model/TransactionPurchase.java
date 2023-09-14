package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "TAB_TRANSACTION")
public class TransactionPurchase {

    //private static final long serialVersionUID = -4739518261390698938L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODG_TRANSACTION")
    private Long idTransaction;
    @Temporal(TemporalType.TIMESTAMP )
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date transactionDate;
    @Column(name = "PURCHASE_AMOUNT", nullable = false)
    private BigDecimal purchaseAmount;
    @Column(name = "DESCRIPTION", length = 50, nullable = true)
    private String description;
}
