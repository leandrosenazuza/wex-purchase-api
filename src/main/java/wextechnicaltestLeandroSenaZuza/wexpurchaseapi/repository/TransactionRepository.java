package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionPurchase, Long> {
}
