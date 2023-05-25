package ru.nsu.ccfit.lopatkin.store.common.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.validator.IsDiscount;
import ru.nsu.ccfit.lopatkin.store.common.validator.IsPrice;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO extends BaseDTO {

    public ProductDTO(Long id, String productName, ProductTypeDTO productType, Integer productSize, SupplierDTO supplier,
                      Double price, Integer discount, String documentId, Boolean isGuarantee, Double customsPrice,
                      LocalDateTime arrivalDate, Double finalPrice, Integer finalDiscount) {
        super(id);
        this.productName = productName;
        this.productType = productType;
        this.productSize = productSize;
        this.supplier = supplier;
        this.price = price;
        this.discount = discount;
        this.documentId = documentId;
        this.isGuarantee = isGuarantee;
        this.customsPrice = customsPrice;
        this.arrivalDate = arrivalDate;
        this.finalPrice = finalPrice;
        this.finalDiscount = finalDiscount;
    }

    @NotBlank
    private String productName;

    @NotNull
    private ProductTypeDTO productType;

    @NotNull
    @Positive
    private Integer productSize;

    @NotNull
    private SupplierDTO supplier;

    @IsPrice
    private Double price;

    @IsDiscount
    private Integer discount;

    private String documentId;

    private Boolean isGuarantee;

    @IsPrice
    private Double customsPrice;

    private LocalDateTime arrivalDate;

    @IsPrice
    private Double finalPrice;

    @IsDiscount
    private Integer finalDiscount;
}
