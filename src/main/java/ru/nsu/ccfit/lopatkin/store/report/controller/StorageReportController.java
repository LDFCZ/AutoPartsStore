package ru.nsu.ccfit.lopatkin.store.report.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.StorageProductReportDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.StorageReportDTO;


@Slf4j
@RestController
@RequestMapping("/reports/storages")
@RequiredArgsConstructor
public class StorageReportController {

    /**
     * Получить перечень, объем и номер ячейки для всех деталей, хранящихся на складе. (Получить инвентаризационную ведомость)
     *
     * @param offset номер страницы (с нуля)
     * @param limit  количество записей на страницу
     * @return Список {@link StorageProductReportDTO}
     * <p>
     * SELECT
     * p.id,
     * p.product_name,
     * scp.count AS volume,
     * sc.id AS cell_number
     * FROM
     * storage_cells_products scp
     * JOIN
     * products p ON scp.product_id = p.id
     * JOIN
     * storage_cells sc ON scp.cell_id = sc.id;
     */
    @GetMapping("/inventory")
    public Page<StorageProductReportDTO> getStorageProductReport(@RequestParam("offset") @Min(0) Integer offset,
                                                                 @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return null;
    }

    /**
     * Подсчитать сколько пустых ячеек на складе и сколько он сможет вместить товара.
     *
     * @return {@link StorageReportDTO}
     * <p>
     * SELECT
     * COUNT(*) AS empty_cells_count,
     * SUM(sc.size) AS total_capacity,
     * SUM(sc.size) - IFNULL(SUM(p.average_size), 0) AS remaining_capacity
     * FROM
     * storage_cells sc
     * LEFT JOIN
     * (
     * SELECT
     * scp.cell_id,
     * AVG(p.product_size) AS average_size
     * FROM
     * storage_cells_products scp
     * JOIN
     * products p ON scp.product_id = p.id
     * GROUP BY
     * scp.cell_id
     * ) p ON sc.id = p.cell_id
     */
    @GetMapping
    public StorageReportDTO getStorageReport() {
        return null;
    }
}
