package ru.nsu.ccfit.lopatkin.store.common.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Supplier;

@Component
public class SupplierMapper {

    public SupplierDTO supplierToSupplierDTO(Supplier supplier) {
        return new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getAddress());
    }

    public Supplier supplierDTOToSupplier(SupplierDTO supplierDTO) {
        return new Supplier(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getAddress());
    }
}
