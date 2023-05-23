package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.DefectRegister;

import java.util.Optional;

public interface DefectRegisterRepository extends JpaRepository<DefectRegister, Long> {

    Optional<DefectRegister> findFirstByOrder_IdAndProduct_Id(Long order_id, Long product_id);
}
