package ru.nsu.ccfit.lopatkin.store.report.controller;

// TODO Получить перечень и общее число поставщиков определенной категории,
// поставляющих указанный вид товара, либо поставивших указанный товар
// в объеме, не менее заданного за определенный период.

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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
     * <p>
     * SELECT
     * COUNT(DISTINCT s.id) AS supplier_count,
     * s.name
     * FROM
     * suppliers s
     * JOIN
     * products p ON s.id = p.supplier_id
     * JOIN
     * product_types pt ON p.product_type_id = pt.id
     * WHERE
     * (
     * pt.product_type_name = 'Вид_товара' -- Замените на конкретный вид товара
     * OR p.product_name = 'Название_товара' -- Замените на конкретное название товара
     * )
     * AND (
     * p.product_size >= минимальный_объем -- Замените на минимальный объем товара
     * OR p.arrival_date BETWEEN 'начало_периода' AND 'конец_периода' -- Замените на конкретный период
     * )
     * GROUP BY
     * s.id, s.name;
     */
    @GetMapping
    public List<SupplierDTO> getSuppliersReportByProductOrProductCount(@RequestParam LocalDate periodStart,
                                                                       @RequestParam LocalDate periodEnd,
                                                                       @RequestParam Long productId,
                                                                       @RequestParam(required = false) Long count) {
        return null;
    }

}
