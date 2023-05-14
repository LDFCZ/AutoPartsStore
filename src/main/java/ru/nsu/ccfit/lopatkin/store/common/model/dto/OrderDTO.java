package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO extends BaseDTO {

    public OrderDTO(Long id, LocalDateTime orderDate, LocalDateTime orderEndDate, ClientDTO client, boolean isCompleted) {
        super(id);
        this.orderDate = orderDate;
        this.orderEndDate = orderEndDate;
        this.client = client;
        this.isCompleted = isCompleted;
    }

    private LocalDateTime orderDate;

    private LocalDateTime orderEndDate;

    @NotBlank
    private ClientDTO client;

    private boolean isCompleted;

    private List<ProductDTO> products = new ArrayList<>();
}
