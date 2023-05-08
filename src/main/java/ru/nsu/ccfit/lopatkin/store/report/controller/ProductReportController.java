package ru.nsu.ccfit.lopatkin.store.report.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ExceptionDTO;

// TODO Получить сведения о конкретном виде деталей: какими поставщиками
// поставляется, их расценки, время поставки.
@Slf4j
@RestController("/reports/products")
public class ProductReportController {

    @GetMapping("/full-info-by-product")
    public ResponseEntity<?> getFullInfoByProduct(@RequestParam Long id) {
        try {
            return null;
        } catch (LogicException e) {
            log.error("Logic error while creating product report: " + e.getMessage(), e);
            return ResponseEntity.badRequest().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
        catch (Exception e) {
            log.error("Error while creating product report: " + e.getMessage(), e);
            return ResponseEntity.internalServerError().body(new ExceptionDTO(e.getMessage(), e.getStackTrace()));
        }
    }
}
