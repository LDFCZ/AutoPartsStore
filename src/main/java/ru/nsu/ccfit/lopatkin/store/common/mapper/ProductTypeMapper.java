package ru.nsu.ccfit.lopatkin.store.common.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductTypeDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ProductType;

@Component
public class ProductTypeMapper {

    public ProductTypeDTO productTypeToProductTypeDTO(ProductType productType) {
        return new ProductTypeDTO(productType.getId(), productType.getProductTypeName());
    }

    public ProductType productTypeDTOTOProductType(ProductTypeDTO productTypeDTO) {
        return new ProductType(productTypeDTO.getId(), productTypeDTO.getProductTypeName());
    }
}
