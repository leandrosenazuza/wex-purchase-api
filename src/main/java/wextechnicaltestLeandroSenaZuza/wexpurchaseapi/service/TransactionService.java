package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.PersistErrorException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.ResourceNotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository.TransactionRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionPurchase findById(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("You have to inform the id");
        }

        final Optional<TransactionPurchase> optional = this.transactionRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found.");
        }

        return optional.get();


    }
    public List<TransactionPurchase> findAll() throws Exception {

        final List<TransactionPurchase> optionalList = this.transactionRepository.findAll();

        if (optionalList.isEmpty()) {
            throw new Exception("Empty list");
        }

        return optionalList;

    }

    public TransactionPurchase createPurchase(final TransactionPurchaseRequest request) throws Exception {
        try{
            return transactionRepository.save(prepareToSave(request));
        }catch (Exception e) {
            throw new PersistErrorException("Error to persist the Transaction.");
        }
    }

    public TransactionPurchase prepareToSave(TransactionPurchaseRequest request){
        TransactionPurchaseDTO transactionPurchaseDTO = new TransactionPurchaseDTO();
        transactionPurchaseDTO.setTransactionDate(new Date());
        transactionPurchaseDTO.setPurchaseAmount(request.getPurchaseAmount());
        if(!request.getDescription().isEmpty()) transactionPurchaseDTO.setDescription(request.getDescription());
        return transactionMapper.toEntity(transactionPurchaseDTO);
    }
}
