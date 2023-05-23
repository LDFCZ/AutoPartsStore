package ru.nsu.ccfit.lopatkin.store.processor.service.primitive;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ProductMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;
import ru.nsu.ccfit.lopatkin.store.common.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public Page<ProductDTO> getPageWithProducts(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return productRepository.findAll(pageable).map(productMapper::productToProductDTO);
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productMapper.productToProductDTO(product.get());
        }
        throw new LogicException("Не найден продукт (деталь) с id: " + id);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productRepository.findFirstByProductName(productDTO.getProductName()).isPresent()) {
            throw new LogicException("Продукт (Деталь) с названием=" + productDTO.getProductName() + " уже существует");
        }
        Product newProduct = productRepository.save(productMapper.productDTOToProduct(productDTO));
        return productMapper.productToProductDTO(newProduct);
    }

    public ProductDTO updateProduct(ProductDTO updatedProductDTO) {
        Optional<Product> oldProductOptional = productRepository.findById(updatedProductDTO.getId());
        if (oldProductOptional.isEmpty()) {
            throw new LogicException("Не найден продукт (деталь) с id: " + updatedProductDTO.getId());
        }
        if (productRepository.findFirstByProductNameAndIdIsNot(updatedProductDTO.getProductName(), updatedProductDTO.getId()).isPresent()) {
            throw new LogicException("Продукт (Деталь) с названием=" + updatedProductDTO.getProductName() + " уже существует");
        }
        Product oldProduct = oldProductOptional.get();
        oldProduct.setProductName(updatedProductDTO.getProductName());
        oldProduct.setProductSize(updatedProductDTO.getProductSize());
        oldProduct.setPrice(updatedProductDTO.getPrice());
        oldProduct.setDiscount(updatedProductDTO.getDiscount());
        oldProduct.setDocumentId(updatedProductDTO.getDocumentId());
        oldProduct.setIsGuarantee(updatedProductDTO.getIsGuarantee());
        oldProduct.setCustomsPrice(updatedProductDTO.getCustomsPrice());
        oldProduct.setArrivalDate(updatedProductDTO.getArrivalDate());
        oldProduct.setFinalPrice(updatedProductDTO.getFinalPrice());
        oldProduct.setDiscount(updatedProductDTO.getFinalDiscount());
        Product updatedProduct = productRepository.save(oldProduct);
        return productMapper.productToProductDTO(updatedProduct);
    }

    public Long deleteProduct(Long id) {
        productRepository.deleteById(id); // Если такого id нет, то мы это игнорируем (ошибок не будет)
        return id;
    }
}
