package ru.nsu.ccfit.lopatkin.store.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.validator.IsPrice;

import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name", length = 150, nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;

    @Column(name = "product_size", nullable = false)
    private Integer productSize;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_product_supplier"))
    private Supplier supplier;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "document_id", length = 150)
    private String documentId;

    @Column(name = "is_guarantee", nullable = false)
    private Boolean isGuarantee;

    @Column(name = "customs_price", nullable = false)
    private Double customsPrice;

    @Column(name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "final_price", nullable = false)
    private Double finalPrice;

    @Column(name = "final_discount", nullable = false)
    private Integer finalDiscount;

}
