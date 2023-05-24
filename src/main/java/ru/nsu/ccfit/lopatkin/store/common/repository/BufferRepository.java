package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Buffer;

import java.util.Optional;

public interface BufferRepository extends JpaRepository<Buffer, Long> {

    Optional<Buffer> findFirstByProduct_IdIs(Long product_id);
}
