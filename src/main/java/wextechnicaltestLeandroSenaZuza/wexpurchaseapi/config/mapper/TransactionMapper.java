package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper;

import org.mapstruct.Mapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.response.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionPurchase toEntity(TransactionPurchase dto);

    TransactionPurchaseDTO toDTO(TransactionPurchase entity);
}
