package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProductId;

import java.util.Optional;

public interface StorageCellsProductsRepository extends JpaRepository<StorageCellProduct, StorageCellProductId> {

    Optional<StorageCellProduct> findFirstByProductIs(Product product);
}
