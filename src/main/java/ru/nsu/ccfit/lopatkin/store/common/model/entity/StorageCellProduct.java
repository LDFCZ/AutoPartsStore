package ru.nsu.ccfit.lopatkin.store.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "storage_cells_products")
public class StorageCellProduct {
    @EmbeddedId
    private StorageCellProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cellId")
    @JoinColumn(name = "cell_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_storage_cell_product_storage_cell"))
    private StorageCell storageCell;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_storage_cell_product_product"))
    private Product product;

    @Column(name = "arrival_date", nullable = false)
    private LocalDateTime arrivalDate;

    @Column(name = "count", nullable = false)
    private Integer count;
}
