package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Supplier;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Optional<Supplier> findFirstByAddressAndName(String address, String name);

    Optional<Supplier> findFirstByAddressAndNameAndIdNot(String address, String name, Long id);
}
