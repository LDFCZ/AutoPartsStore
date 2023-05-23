package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;

import java.time.LocalDateTime;

/**
 * ДТО содержащее информацию о бракованном товаре (детали)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DefectProductDTO {

    private ProductDTO productDTO;

    private String cause;

    private LocalDateTime issueDate;

    private SupplierDTO supplier;
}
