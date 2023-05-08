package ru.nsu.ccfit.lopatkin.store.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "big_suppliers")
public class BigSupplier extends Supplier {

    @Column(name = "license_id", length = 150)
    private String licenseId;

    @Column(name = "customs_tax")
    private Integer customsTax;

}
