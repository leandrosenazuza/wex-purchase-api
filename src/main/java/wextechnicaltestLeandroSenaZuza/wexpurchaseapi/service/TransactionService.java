package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository.TransactionRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionPurchase findById(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new Exception("You have to inform the id");
        }

        final Optional<TransactionPurchase> optional = this.transactionRepository.findById(id);

        if (optional.isEmpty()) {
            throw new Exception("Not Found.");
        }

        return optional.get();


    }
}
