package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;

import java.util.List;
import java.util.Map;

/**
 * Перечень, общее количество и стоимость товара
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CashReportDTO {

    // product + count
    private  Map<ProductDTO, Integer> products;

    private Integer count;

    private Integer sum;
}
