package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

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
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;

/**
 * Контроллер для работы с поставщиками
 */
@Slf4j
@RestController
@RequestMapping("/processing/suppliers")
public class SupplierController {

    @GetMapping("/all-suppliers")
    public Page<SupplierDTO> getSuppliers() {
        return null;
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplier(@PathVariable Long id) {
        return null;
    }

    @PostMapping("/new")
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return null;
    }

    @PutMapping("/update")
    public SupplierDTO updateSupplier(@RequestBody SupplierDTO supplierDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteSupplier(@PathVariable Long id) {
        return null;
    }
}
