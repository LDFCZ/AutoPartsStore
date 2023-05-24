package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProduct;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ClientOrderProductId;

public interface ClientOrderProductRepository extends JpaRepository<ClientOrderProduct, ClientOrderProductId> {
}
