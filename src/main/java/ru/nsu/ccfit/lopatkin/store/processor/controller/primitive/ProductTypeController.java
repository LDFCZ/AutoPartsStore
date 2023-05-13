package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductTypeDTO;

/**
 * Контроллер для работы с типами продуктов (запчастей)
 */
@Slf4j
@RestController
@RequestMapping("/processing/product-types")
public class ProductTypeController {

    @GetMapping("/all-product-types")
    public ResponseEntity<?> getProductTypes() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductType(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProductType(@RequestBody ProductTypeDTO productTypeDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProductType(@PathVariable String id) {
        return null;
    }
}
