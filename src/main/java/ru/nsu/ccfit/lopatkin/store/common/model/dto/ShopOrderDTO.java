package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShopOrderDTO extends BaseDTO {

    public ShopOrderDTO(Long id, LocalDateTime orderDate, ProductDTO product, Integer totalCount, Integer doneCount, String contractId) {
        super(id);
        this.orderDate = orderDate;
        this.product = product;
        this.totalCount = totalCount;
        this.doneCount = doneCount;
        this.contractId = contractId;
    }

    private LocalDateTime orderDate;

    private ProductDTO product;

    private Integer totalCount;

    private Integer doneCount;

    private String contractId;
}
