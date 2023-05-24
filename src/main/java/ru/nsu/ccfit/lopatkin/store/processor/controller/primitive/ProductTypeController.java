package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductTypeDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.primitive.ProductTypeService;

import java.util.List;

/**
 * Контроллер для работы с типами продуктов (запчастей)
 */
@Slf4j
@RestController
@RequestMapping("/processing/product-types")
@RequiredArgsConstructor
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping("/all-product-types-page")
    public Page<ProductTypeDTO> getProductTypesPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return productTypeService.getPageWithProductTypes(offset, limit);
    }

    @GetMapping("/all-product-types")
    public List<ProductTypeDTO> getProductTypes() {
        return productTypeService.getProductTypes();
    }

    @GetMapping("/{id}")
    public ProductTypeDTO getProductType(@PathVariable Long id) {
        return productTypeService.getProductTypeById(id);
    }

    @PostMapping("/new")
    public ProductTypeDTO createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return productTypeService.createProductType(productTypeDTO);
    }


    @DeleteMapping("/delete/{id}")
    public Long deleteProductType(@PathVariable Long id) {
        return productTypeService.deleteProductType(id);
    }
}
