package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ExceptionDTO;

import java.time.LocalDate;

// TODO Вывести в порядке возрастания десять самых продаваемых деталей и
// десять самых "дешевых" поставщиков.

// TODO Получить среднее число продаж на месяц по любому виду деталей.

// TODO Получить долю товара конкретного поставщика в процентах, деньгах,
// единицах от всего оборота магазина прибыль магазина за указанный
// период.

// TODO Получить накладные расходы в процентах от объема продаж.

// TODO Получить перечень, общее количество и стоимость товара,
// реализованного за конкретный день.

// TODO Получить кассовый отчет за определенный период. (то же самое, что и предыдущее только в периоде)

// TODO Получить скорость оборота денежных средств, вложенных в товар (как
// товар быстро продается).

// TODO Получить перечень и общее количество заявок от покупателей на
// ожидаемый товар, подсчитать на какую сумму даны заявки.
@Slf4j
@RestController("/reports/sales")
public class SalesReportController {

    @GetMapping("/most-selling-products")
    public ResponseEntity<?> getMostSellingProducts() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/cheapest-suppliers")
    public ResponseEntity<?> getCheapestSuppliers() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/mean-product-sales")
    public ResponseEntity<?> getMeanSalesCountOnMonthByProduct(@RequestParam Long productId) {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/supplier-share")
    public ResponseEntity<?> getSupplierShare(@RequestParam Long supplierId) {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/profit")
    public ResponseEntity<?> getStoreProfit() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/overheads")
    public ResponseEntity<?> getStoreOverheads() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/cash-report-by-period")
    public ResponseEntity<?> getCashReportByPeriod(@RequestParam LocalDate periodStart,
                                                   @RequestParam(required = false) LocalDate periodEnd) {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/turnover-rate")
    public ResponseEntity<?> getRateOfTurnover() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }

    @GetMapping("/outstanding-orders")
    public ResponseEntity<?> getOutstandingOrderReport() {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating sales report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }
}
