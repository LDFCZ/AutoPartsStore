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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;

/**
 * Контроллер для работы с продуктами (запчастями)
 */
@Slf4j
@RestController
@RequestMapping("/processing/products")
public class ProductController {

    @GetMapping("/all-products")
    public ResponseEntity<?> getProducts() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        return null;
    }
}
