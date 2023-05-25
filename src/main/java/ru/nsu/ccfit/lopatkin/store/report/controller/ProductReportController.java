package ru.nsu.ccfit.lopatkin.store.report.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.ProductReportDTO;

@Slf4j
@RestController
@RequestMapping("/reports/products")
@RequiredArgsConstructor
public class ProductReportController {

    /**
     * Получить сведения о конкретном виде деталей: какими поставщиками
     * поставляется, их расценки, время поставки.
     *
     * @param id id продукта (детали)
     * @return {@link ProductReportDTO}
     *
     *
     * SELECT p.id AS product_id, p.product_name, s.id AS supplier_id, s.name AS supplier_name, p.price AS supplier_price
     * FROM products p
     * JOIN suppliers s ON p.supplier_id = s.id
     * WHERE p.product_type_id = <product_type_id>;
     */
    @GetMapping("/full-info-by-product")
    public ProductReportDTO getFullInfoByProduct(@RequestParam Long id) {
        return null;
    }
}
