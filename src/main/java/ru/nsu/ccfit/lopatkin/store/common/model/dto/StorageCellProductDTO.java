package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageCellProductDTO {

    private ProductDTO product;

    private Integer count;
}
