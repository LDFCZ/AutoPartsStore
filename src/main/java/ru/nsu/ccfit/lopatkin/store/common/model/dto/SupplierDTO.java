package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDTO extends BaseDTO {

    public SupplierDTO(Long id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    @NotBlank
    private String name;

    @NotBlank
    private String address;
}
