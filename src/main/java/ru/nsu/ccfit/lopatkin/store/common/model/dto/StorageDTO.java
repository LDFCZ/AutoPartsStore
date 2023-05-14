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
public class StorageDTO extends BaseDTO {

    public StorageDTO(Long id, String address, Integer maxSize) {
        super(id);
        this.address = address;
        this.maxSize = maxSize;
    }

    @NotBlank
    private String address;

    @NotBlank
    @Positive
    private Integer maxSize;

    private List<StorageCellDTO> storageCells = new ArrayList<>();

}
