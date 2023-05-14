package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfferDTO extends BaseDTO {

    public OfferDTO(Long id, ProductDTO product, SupplierDTO supplier, Integer count) {
        super(id);
        this.product = product;
        this.supplier = supplier;
        this.count = count;
    }

    private ProductDTO product;

    private SupplierDTO supplier;

    @Positive
    private Integer count;
}
