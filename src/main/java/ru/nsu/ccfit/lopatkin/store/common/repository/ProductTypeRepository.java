package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ProductType;

import java.util.Optional;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    Optional<ProductType> findFirstByProductTypeName(String productTypeName);

    Optional<ProductType> findFirstByProductTypeNameAndIdNot(String productTypeName, Long id);
}
