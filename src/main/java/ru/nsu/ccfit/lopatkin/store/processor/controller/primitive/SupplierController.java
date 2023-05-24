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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.primitive.SupplierService;

import java.util.List;

/**
 * Контроллер для работы с поставщиками
 */
@Slf4j
@RestController
@RequestMapping("/processing/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/all-suppliers-page")
    public Page<SupplierDTO> getSuppliersPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                          @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return supplierService.getPageWithSuppliers(offset, limit);
    }

    @GetMapping("/all-suppliers")
    public List<SupplierDTO> getSuppliers() {
        return supplierService.getSuppliers();
    }


    @PostMapping("/new")
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

}
