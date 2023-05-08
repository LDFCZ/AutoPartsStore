package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ExceptionDTO;

import java.time.LocalDate;

// TODO Получить перечень и общее число покупателей, купивших указанный вид
// товара за некоторый период, либо сделавших покупку товара в объеме, не
// менее указанного.
@Slf4j
@RestController("/reports/clients")
public class ClientsReportController {

    @GetMapping("/by-product-and-period-or-product-count")
    public ResponseEntity<?> getClientListByProductAndPeriodOrByProductCount(@RequestParam Long productId,
                                                                             @RequestParam LocalDate periodStart,
                                                                             @RequestParam LocalDate periodEnd,
                                                                             @RequestParam(required = false) Long count) {
        try {
            if (count != null) {
                return null;
            }
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating client report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating client report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }
}
