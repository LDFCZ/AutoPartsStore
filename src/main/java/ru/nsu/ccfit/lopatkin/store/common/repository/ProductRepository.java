package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByProductSizeDesc();

    Optional<Product> findFirstByProductName(String productName);

    Optional<Product> findFirstByProductNameAndIdIsNot(String productName, Long id);
}
