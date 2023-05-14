package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Long, Supplier> {
}
