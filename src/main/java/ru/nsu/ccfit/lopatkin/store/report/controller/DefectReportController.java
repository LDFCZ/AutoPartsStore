package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ExceptionDTO;

import java.time.LocalDate;

// TODO Получить перечень и общее количество бракованного товара,
// пришедшего за определенный период и список поставщиков,
// поставивших товар.
@Slf4j
@RestController("/reports/defects")
public class DefectReportController {

    @GetMapping("/by-period")
    public ResponseEntity<?> getDefectProductListByPeriod(@RequestParam LocalDate periodStart,
                                                          @RequestParam LocalDate periodEnd) {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating defect report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating defect report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }
}
