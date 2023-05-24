package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.primitive.ProductService;

import java.util.List;

/**
 * Контроллер для работы с продуктами (запчастями)
 */
@Slf4j
@RestController
@RequestMapping("/processing/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all-products-page")
    public Page<ProductDTO> getProductsPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                        @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.info("Запрос на получение страницы с продуктами (деталями)");
        return productService.getPageWithProducts(offset, limit);
    }

    //TODO фильтр по имени

    @GetMapping("/all-products")
    public List<ProductDTO> getProducts() {
        log.info("Запрос на получение всех продуктов (деталей)");
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        log.info("Запрос на получение продукта (детали) с id={}", id);
        return productService.getProductById(id);
    }

    @PostMapping("/new")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info("Запрос на создание нового продукта (детали) с именем={}", productDTO.getProductName());
        return productService.createProduct(productDTO);
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info("Запрос на редактирование продукта (детали) с именем={}", productDTO.getProductName());
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        log.info("Запрос на удаление продукта (детали) с id={}", id);
        return productService.deleteProduct(id);
    }
}
