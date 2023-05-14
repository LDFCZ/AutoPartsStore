package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DefectDTO extends BaseDTO {

    public DefectDTO(Long id, LocalDateTime issueDate, OrderDTO order, ProductDTO product, String cause) {
        super(id);
        this.issueDate = issueDate;
        this.order = order;
        this.product = product;
        this.cause = cause;
    }

    private LocalDateTime issueDate;

    private OrderDTO order;


    private ProductDTO product;

    @NotBlank
    private String cause;
}
