package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.NotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.PersistErrorException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.ResourceNotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseAnyTimeRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseNowRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository.TransactionRepository;

import java.text.SimpleDateFormat;
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
            throw new NotFoundException("Empty table! You have to persist one transaction.");
        }

        return optionalList;

    }

    public TransactionPurchase createPurchase(final TransactionPurchaseNowRequest request) throws Exception {
        try{
            return transactionRepository.save(prepareToSave(request));
        }catch (Exception e) {
            throw new PersistErrorException("Error to persist the Transaction.");
        }
    }

    public TransactionPurchase createPurchaseAnyTime(final TransactionPurchaseAnyTimeRequest request) throws Exception {
        try{
            return transactionRepository.save(prepareToSaveWithPreDeterminetedTime(request));
        }catch (Exception e) {
            throw new PersistErrorException("Error to persist the Transaction.");
        }
    }

    public TransactionPurchase prepareToSave(TransactionPurchaseNowRequest request) {
        TransactionPurchaseDTO transactionPurchaseDTO = new TransactionPurchaseDTO();

        Date currentDate = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(currentDate);

        transactionPurchaseDTO.setTransactionDate(formattedDate);
        transactionPurchaseDTO.setPurchaseAmount(request.getPurchaseAmount());

        if (!request.getDescription().isEmpty()) {
            transactionPurchaseDTO.setDescription(request.getDescription());
        }

        return transactionMapper.toEntity(transactionPurchaseDTO);
    }

    public TransactionPurchase prepareToSaveWithPreDeterminetedTime(TransactionPurchaseAnyTimeRequest request){
        TransactionPurchaseDTO transactionPurchaseDTO = new TransactionPurchaseDTO();
        transactionPurchaseDTO.setTransactionDate(request.getTransactionDate());
        transactionPurchaseDTO.setPurchaseAmount(request.getPurchaseAmount());
        if(!request.getDescription().isEmpty()) transactionPurchaseDTO.setDescription(request.getDescription());
        return transactionMapper.toEntity(transactionPurchaseDTO);
    }


}
