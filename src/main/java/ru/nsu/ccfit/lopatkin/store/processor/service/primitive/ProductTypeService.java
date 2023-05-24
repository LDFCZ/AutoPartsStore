package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ProductTypeMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductTypeDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ProductType;
import ru.nsu.ccfit.lopatkin.store.common.repository.ProductTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeMapper productTypeMapper;

    private final ProductTypeRepository productTypeRepository;


    public Page<ProductTypeDTO> getPageWithProductTypes(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return productTypeRepository.findAll(pageable).map(productTypeMapper::productTypeToProductTypeDTO);
    }

    public List<ProductTypeDTO> getProductTypes() {
        return productTypeRepository.findAll().stream().map(productTypeMapper::productTypeToProductTypeDTO).toList();
    }

    public ProductTypeDTO getProductTypeById(Long id) {
        Optional<ProductType> productType = productTypeRepository.findById(id);
        if (productType.isPresent()) {
            return productTypeMapper.productTypeToProductTypeDTO(productType.get());
        }
        throw new LogicException("Не найден тип продукта с id: " + id);
    }

    public ProductTypeDTO createProductType(ProductTypeDTO productTypeDTO) {
        if (productTypeRepository.findFirstByProductTypeName(productTypeDTO.getProductTypeName()).isPresent()) {
            throw new LogicException("Тип продукта с названием=" + productTypeDTO.getProductTypeName() + " уже существует");
        }
        ProductType newProductType = productTypeRepository.save(productTypeMapper.productTypeDTOTOProductType(productTypeDTO));
        return productTypeMapper.productTypeToProductTypeDTO(newProductType);
    }

    public ProductTypeDTO updateProductType(ProductTypeDTO updatedProductTypeDTO) {
        Optional<ProductType> oldProductTypeOptional = productTypeRepository.findById(updatedProductTypeDTO.getId());
        if (oldProductTypeOptional.isEmpty()) {
            throw new LogicException("Не найден тип продукта с id: " + updatedProductTypeDTO.getId());
        }
        if (productTypeRepository.findFirstByProductTypeNameAndIdNot(updatedProductTypeDTO.getProductTypeName(), updatedProductTypeDTO.getId()).isPresent()) {
            throw new LogicException("Тип продукта с названием=" + updatedProductTypeDTO.getProductTypeName() + " уже существует");
        }
        ProductType oldProductType = oldProductTypeOptional.get();
        oldProductType.setProductTypeName(updatedProductTypeDTO.getProductTypeName());
        ProductType updatedProduct = productTypeRepository.save(oldProductType);
        return productTypeMapper.productTypeToProductTypeDTO(updatedProduct);
    }

    public Long deleteProductType(Long id) {
        productTypeRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }
}
