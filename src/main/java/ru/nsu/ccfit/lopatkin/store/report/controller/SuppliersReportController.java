package ru.nsu.ccfit.lopatkin.store.report.controller;

// TODO Получить перечень и общее число поставщиков определенной категории,
// поставляющих указанный вид товара, либо поставивших указанный товар
// в объеме, не менее заданного за определенный период.

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports/suppliers")
public class SuppliersReportController {

    /**
     * Получить перечень и общее число поставщиков определенной категории,
     * поставляющих указанный вид товара, либо поставивших указанный товар
     * в объеме, не менее заданного за определенный период.
     *
     * @param periodStart Начало периода
     * @param periodEnd   Конец периода
     * @param productId   id продукта
     * @param count       Количество продукта
     * @return Список {@link SupplierDTO}
     */
    @GetMapping
    public List<SupplierDTO> getSuppliersReportByProductOrProductCount(@RequestParam LocalDate periodStart,
                                                                       @RequestParam LocalDate periodEnd,
                                                                       @RequestParam Long productId,
                                                                       @RequestParam(required = false) Long count) {
        return null;
    }

}
