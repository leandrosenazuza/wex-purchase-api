package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.BadParamRequestException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.NotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.ResourceNotFoundException;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper.TransactionMapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository.TransactionRepository;

import java.text.ParseException;
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

    public TransactionPurchase findById(Long id){
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("You have to inform the id");
        }

        final Optional<TransactionPurchase> optional = this.transactionRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Transaction with id " + id + " not found.");
        }

        return optional.get();

    }
    public List<TransactionPurchase> findAll() {

        final List<TransactionPurchase> optionalList = this.transactionRepository.findAll();

        if (optionalList.isEmpty()) {
            throw new NotFoundException("Empty table! You have to persist one transaction.");
        }

        return optionalList;

    }

    public TransactionPurchase createPurchase(final TransactionPurchaseRequest request) {
        TransactionPurchase transactionPurchase = prepareToSaveWithPreDeterminetedTime(request);
        return transactionRepository.save(transactionPurchase);
    }

    public TransactionPurchase prepareToSaveWithPreDeterminetedTime(TransactionPurchaseRequest request){
        TransactionPurchase transactionPurchase = new TransactionPurchase();
        transactionPurchase.setDescription(request.getDescription());
        transactionPurchase.setPurchaseAmount(request.getPurchaseAmount());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
          Date date = formatter.parse(request.getTransactionDate());
          transactionPurchase.setTransactionDate(date);
        } catch (ParseException e) {
            throw new BadParamRequestException("You are passing not valid values in the request. Your date should be in the format: yyyy-MM-dd");

        }

        return transactionPurchase;
    }

}
