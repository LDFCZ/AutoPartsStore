package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.StorageCell;

import java.util.List;
import java.util.Optional;

public interface StorageCellRepository extends JpaRepository<StorageCell, Long> {

    List<StorageCell> findAllByOrderByFreeSpaceDesc();

    Optional<StorageCell> findFirstByFreeSpaceIsGreaterThanOrderByFreeSpaceDesc(Integer freeSpace);
}
