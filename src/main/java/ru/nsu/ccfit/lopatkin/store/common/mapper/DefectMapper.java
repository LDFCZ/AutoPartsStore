package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.DefectDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.DefectRegister;

@Component
@RequiredArgsConstructor
public class DefectMapper {

    private final ClientOrderMapper clientOrderMapper;

    private final ProductMapper productMapper;

    public DefectDTO defectRegisterToDefectDTO(DefectRegister defectRegister) {
        return new DefectDTO(
                defectRegister.getId(),
                defectRegister.getIssueDate(),
                clientOrderMapper.clientOrderToClientOrderDTO(defectRegister.getOrder()),
                productMapper.productToProductDTO(defectRegister.getProduct()),
                defectRegister.getCause()
        );
    }

    public DefectRegister defectDTOToDefectRegister(DefectDTO defectDTO) {
        return new DefectRegister(defectDTO.getId(),
                defectDTO.getIssueDate(),
                clientOrderMapper.clientOrderDTOToClientOrder(defectDTO.getOrder()),
                productMapper.productDTOToProduct(defectDTO.getProduct()),
                defectDTO.getCause()
        );
    }
}
