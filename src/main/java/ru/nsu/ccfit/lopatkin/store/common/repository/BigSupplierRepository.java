package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.BigSupplier;

public interface BigSupplierRepository extends JpaRepository<Long, BigSupplier> {
}
