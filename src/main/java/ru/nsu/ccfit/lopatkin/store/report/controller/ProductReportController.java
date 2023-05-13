package ru.nsu.ccfit.lopatkin.store.report.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.ProductReportDTO;

@Slf4j
@RestController
@RequestMapping("/reports/products")
public class ProductReportController {

    /**
     * Получить сведения о конкретном виде деталей: какими поставщиками
     * поставляется, их расценки, время поставки.
     *
     * @param id id продукта (детали)
     * @return {@link ProductReportDTO}
     */
    @GetMapping("/full-info-by-product")
    public ProductReportDTO getFullInfoByProduct(@RequestParam Long id) {
        return null;
    }
}
