package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.mapper;

import org.mapstruct.Mapper;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.dto.TransactionPurchaseDTO;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.model.TransactionPurchase;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    TransactionPurchase toEntity(TransactionPurchaseDTO dto);

    TransactionPurchaseDTO toDTO(TransactionPurchase entity);

    List<TransactionPurchaseDTO> toListDTO(List<TransactionPurchase> entityList);
}
