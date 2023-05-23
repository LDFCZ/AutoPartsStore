package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.ShopOrder;

import java.util.List;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {

    @Query("SELECT so FROM ShopOrder so WHERE NOT EXISTS (SELECT cso FROM CompletedShopOrder cso WHERE cso.shopOrder = so)")
    List<ShopOrder> findUncompletedOrders();

}
