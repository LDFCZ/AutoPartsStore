package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCellProductId;

import java.util.List;
import java.util.Optional;

public interface StorageCellsProductsRepository extends JpaRepository<StorageCellProduct, StorageCellProductId> {

    Optional<StorageCellProduct> findFirstByProductIs(Product product);

    List<StorageCellProduct> findAllByProduct_IdIsOrderByCountAsc(Long product_id);

    @Query("SELECT SUM(e.count) FROM StorageCellProduct e WHERE e.product.id = :productId")
    Integer getAmountOfProductById(Long productId);
}
