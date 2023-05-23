package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.CashReportDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.SupplierShareDTO;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports/sales")
public class SalesReportController {

    /**
     * Вывести в порядке возрастания десять самых продаваемых деталей.
     *
     * @return Список товаров {@link ProductDTO}
     */
    @GetMapping("/most-selling-products")
    public List<ProductDTO> getMostSellingProducts() {
        return null;
    }

    /**
     * Вывести в порядке возрастания десять самых "дешевых" поставщиков.
     *
     * @return Список поставщиков {@link SupplierDTO}
     */
    @GetMapping("/cheapest-suppliers")
    public List<SupplierDTO> getCheapestSuppliers() {
        return null;
    }

    /**
     * Получить среднее число продаж на месяц по любому виду деталей.
     *
     * @param productId id продукта (детали)
     * @return Число)
     */
    @GetMapping("/mean-product-sales")
    public Integer getMeanSalesCountOnMonthByProduct(@RequestParam Long productId) {
        return null;
    }

    /**
     * Получить долю товара конкретного поставщика в процентах, деньгах,
     * единицах от всего оборота магазина.
     *
     * @param supplierId id поставщика
     * @return {@link SupplierShareDTO}
     */
    @GetMapping("/supplier-share")
    public SupplierShareDTO getSupplierShare(@RequestParam Long supplierId) {
        return null;
    }

    /**
     * Получить прибыль магазина за указанный период.
     *
     * @param periodStart начало периода
     * @param periodEnd   конец периода
     * @return Прибыль)
     */
    @GetMapping("/profit")
    public Float getStoreProfit(@RequestParam LocalDate periodStart, @RequestParam LocalDate periodEnd) {
        return null;
    }

    /**
     * Получить накладные расходы в процентах от объема продаж.
     *
     * @return Расходы)
     */
    @GetMapping("/overheads")
    public Float getStoreOverheads() {
        return null;
    }

    /**
     * Получить перечень, общее количество и стоимость товара,
     * реализованного за конкретный день. (Получить кассовый отчет за определенный период)
     *
     * @param periodStart - начало периода или день за который нужно смотреть
     * @param periodEnd   - конец периода (null если смотрим в разрезе одного дня)
     * @return {@link CashReportDTO}
     */
    @GetMapping("/cash-report-by-period")
    public CashReportDTO getCashReportByPeriod(@RequestParam LocalDate periodStart,
                                               @RequestParam(required = false) LocalDate periodEnd) {
        return null;
    }

    /**
     * Получить скорость оборота денежных средств, вложенных в товар (как товар быстро продается).
     *
     * @return количество проданного товара * цена товара / количество купленного * цена покупки
     */
    @GetMapping("/turnover-rate/{id}")
    public Float getRateOfTurnover(@PathVariable String id) {
        return null;
    }

    /**
     * Получить перечень и общее количество заявок от покупателей на
     * ожидаемый товар, подсчитать на какую сумму даны заявки.
     *
     * @return {@link ClientOrderDTO}
     */
    @GetMapping("/outstanding-orders")
    public ClientOrderDTO getOutstandingOrderReport() {
        return null;
    }
}
