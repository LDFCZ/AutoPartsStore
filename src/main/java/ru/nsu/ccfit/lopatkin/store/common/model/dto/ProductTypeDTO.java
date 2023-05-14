package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductTypeDTO extends BaseDTO {

    public ProductTypeDTO(Long id, String productTypeName) {
        super(id);
        this.productTypeName = productTypeName;
    }

    @NotBlank
    private String productTypeName;
}
