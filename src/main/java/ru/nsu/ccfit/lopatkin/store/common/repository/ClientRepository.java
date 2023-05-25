package ru.nsu.ccfit.lopatkin.store.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.nsu.ccfit.lopatkin.store.common.model.entity.Client;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByEmail(String email);

    Optional<Client> findClientByEmailAndIdNot(String email, Long id);

    @Query("SELECT c FROM Client c " +
            "JOIN c.orders co " +
            "JOIN co.products p " +
            "WHERE (p.id.productId = :productTypeId OR p.count >= :minCount) " +
            "AND co.orderDate >= :startDate " +
            "AND co.orderDate <= :endDate")
    List<Client> findClientsByProductOrMinPriceAndOrderDateBetween(@Param("productTypeId") Long productId,
                                                                   @Param("minCount") Integer minCount,
                                                                   @Param("startDate") LocalDate startDate,
                                                                   @Param("endDate") LocalDate endDate);

}
