package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.request.TransactionPurchaseRequest;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.repository.TransactionRepository;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.service.TransactionService;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(transactionRepository.save(any(TransactionPurchase.class))).thenReturn(new TransactionPurchase());
    }

    @Test
    public void testCreatePurchase() {

        TransactionPurchaseRequest request = new TransactionPurchaseRequest();
        request.setDescription("Test Purchase");
        request.setPurchaseAmount(BigDecimal.valueOf(100000.0));
        request.setTransactionDate("2023-06-01");

        TransactionPurchase resultTransaction = transactionService.createPurchase(request);

        verify(transactionRepository).save(any(TransactionPurchase.class));

        assertNotNull(resultTransaction);
    }
}
