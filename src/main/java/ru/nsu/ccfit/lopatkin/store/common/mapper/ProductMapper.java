package ru.nsu.ccfit.lopatkin.store.common.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final SupplierMapper supplierMapper;

    private final ProductTypeMapper productTypeMapper;

    public ProductDTO productToProductDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                productTypeMapper.productTypeToProductTypeDTO(product.getProductType()),
                product.getProductSize(),
                supplierMapper.supplierToSupplierDTO(product.getSupplier()),
                product.getPrice(),
                product.getDiscount(),
                product.getDocumentId(),
                product.getIsGuarantee(),
                product.getCustomsPrice(),
                product.getArrivalDate(),
                product.getFinalPrice(),
                product.getFinalDiscount()
                );
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.getId(),
                productDTO.getProductName(),
                productTypeMapper.productTypeDTOTOProductType(productDTO.getProductType()),
                productDTO.getProductSize(),
                supplierMapper.supplierDTOToSupplier(productDTO.getSupplier()),
                productDTO.getPrice(),
                productDTO.getDiscount(),
                productDTO.getDocumentId(),
                productDTO.getIsGuarantee(),
                productDTO.getCustomsPrice(),
                productDTO.getArrivalDate(),
                productDTO.getFinalPrice(),
                productDTO.getFinalDiscount()
                );
    }
}
