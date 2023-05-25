package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SalesReportController {

    /**
     * Вывести в порядке возрастания десять самых продаваемых деталей.
     *
     * @return Список товаров {@link ProductDTO}
     * <p>
     * SELECT p.id AS product_id, p.product_name, COUNT(*) AS total_sales
     * FROM client_orders_products cop
     * JOIN products p ON cop.product_id = p.id
     * GROUP BY p.id, p.product_name
     * ORDER BY total_sales ASC
     * LIMIT 10;
     */
    @GetMapping("/most-selling-products")
    public List<ProductDTO> getMostSellingProducts() {
        return null;
    }

    /**
     * Вывести в порядке возрастания десять самых "дешевых" поставщиков.
     *
     * @return Список поставщиков {@link SupplierDTO}
     * <p>
     * SELECT s.id AS supplier_id, s.name AS supplier_name, MIN(p.price) AS min_price
     * FROM products p
     * JOIN suppliers s ON p.supplier_id = s.id
     * GROUP BY s.id, s.name
     * ORDER BY min_price ASC
     * LIMIT 10;
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
     * <p>
     * SELECT EXTRACT(MONTH FROM o.order_date) AS month,
     * EXTRACT(YEAR FROM o.order_date) AS year,
     * AVG(COUNT(cop.order_id)) AS average_sales
     * FROM client_orders_products cop
     * JOIN client_orders o ON cop.order_id = o.id
     * JOIN products p ON cop.product_id = p.id
     * WHERE p.product_type_id = <product_type_id>
     * GROUP BY EXTRACT(MONTH FROM o.order_date), EXTRACT(YEAR FROM o.order_date);
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
     * <p>
     * SELECT
     * s.id AS supplier_id,
     * s.name AS supplier_name,
     * SUM(p.final_price * cop.count) AS total_revenue,
     * SUM(p.final_price * cop.count) / (SELECT SUM(p.final_price * cop.count) FROM client_orders_products cop JOIN products p ON cop.product_id = p.id) * 100 AS revenue_percentage,
     * SUM(cop.count) AS total_units
     * FROM
     * client_orders_products cop
     * JOIN
     * products p ON cop.product_id = p.id
     * JOIN
     * suppliers s ON p.supplier_id = s.id
     * GROUP BY
     * s.id, s.name;
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
     * <p>
     * SELECT
     * SUM((p.final_price - p.price) * cop.count) AS total_profit
     * FROM
     * client_orders_products cop
     * JOIN
     * products p ON cop.product_id = p.id
     * JOIN
     * client_orders o ON cop.order_id = o.id
     * WHERE
     * o.order_date >= 'начало_периода' AND o.order_date <= 'конец_периода';
     */
    @GetMapping("/profit")
    public Float getStoreProfit(@RequestParam LocalDate periodStart, @RequestParam LocalDate periodEnd) {
        return null;
    }

    /**
     * Получить накладные расходы в процентах от объема продаж.
     *
     * @return Расходы)
     * <p>
     * SELECT
     * (SUM(p.price + p.customs_price) / SUM(p.final_price * cop.count)) * 100 AS overhead_expenses_percentage
     * FROM
     * client_orders_products cop
     * JOIN
     * products p ON cop.product_id = p.id;
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
     * <p>
     * SELECT
     * p.product_name,
     * SUM(cop.count) AS total_quantity,
     * SUM(p.final_price * cop.count) AS total_price
     * FROM
     * client_orders_products cop
     * JOIN
     * products p ON cop.product_id = p.id
     * JOIN
     * client_orders o ON cop.order_id = o.id
     * WHERE
     * o.order_date >= 'начало_периода' AND o.order_date <= 'конец_периода'
     * GROUP BY
     * p.product_name;
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
     * <p>
     * SELECT
     * (SUM(cop.count) * p.final_price) / (SUM(cop.count) * (p.price + p.customs_price)) AS turnover_speed
     * FROM
     * client_orders_products cop
     * JOIN
     * products p ON cop.product_id = p.id;
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
     * <p>
     * SELECT
     * o.id,
     * o.order_date,
     * SUM(p.final_price * cop.count) AS total_amount
     * FROM
     * client_orders o
     * JOIN
     * client_orders_products cop ON o.id = cop.order_id
     * JOIN
     * products p ON cop.product_id = p.id
     * WHERE
     * o.is_completed = false
     * GROUP BY
     * o.id, o.order_date;
     */
    @GetMapping("/outstanding-orders")
    public ClientOrderDTO getOutstandingOrderReport() {
        return null;
    }
}
