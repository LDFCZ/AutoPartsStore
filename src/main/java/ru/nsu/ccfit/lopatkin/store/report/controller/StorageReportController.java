package ru.nsu.ccfit.lopatkin.store.report.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class StorageReportController {

    /**
     * Получить перечень, объем и номер ячейки для всех деталей, хранящихся на складе. (Получить инвентаризационную ведомость)
     *
     * @param offset номер страницы (с нуля)
     * @param limit  количество записей на страницу
     * @return Список {@link StorageProductReportDTO}
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
     */
    @GetMapping
    public StorageReportDTO getStorageReport() {
        return null;
    }
}
