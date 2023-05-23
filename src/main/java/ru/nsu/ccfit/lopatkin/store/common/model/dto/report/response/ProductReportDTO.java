package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;

import java.util.Map;

/**
 * Сведения по конкретному продукту (детали): какими поставщиками
 * поставляется, их расценки, время поставки.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductReportDTO {

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class SupplierData {

        private Integer deliveryTime;

        private Double price;
    }

    private ProductDTO productDTO;

    private Map<SupplierDTO, SupplierData> suppliers;

}


