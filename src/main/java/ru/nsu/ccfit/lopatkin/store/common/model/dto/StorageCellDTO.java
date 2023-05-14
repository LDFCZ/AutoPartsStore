package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageCellDTO extends BaseDTO {

    public StorageCellDTO(Long id, Integer size, Integer freeSpace) {
        super(id);
        this.size = size;
        this.freeSpace = freeSpace;
    }

    @NotBlank
    @Positive
    private Integer size;

    @NotBlank
    @Positive
    private Integer freeSpace;

    private List<ProductDTO> products = new ArrayList<>();

}
