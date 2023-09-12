package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "TAB_PURCHASE")
@SequenceGenerator(name = "SEQUENCE", sequenceName = "TAB_S_PURCHASE", allocationSize = 1)
public class Transaction {

    private static final long serialVersionUID = -4739518261390698938L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQUENCE")
    @Column(name = "CODG_TRANSACTION")
    private Integer idTransaction;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date transactionDate;
    @Column(name = "PURCHASE_AMOUNT", nullable = false)
    private float purchaseAmount;
    @Column(name = "DESCRIPTION", length = 50, nullable = true)
    private String description;
}
