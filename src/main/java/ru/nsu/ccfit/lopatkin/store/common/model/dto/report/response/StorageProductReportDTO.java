package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageCellDTO;

import java.util.List;

/**
 * Объем и номера ячеек для детали, хранящейся на складе
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageProductReportDTO {

    private ProductDTO product;

    private List<StorageCellDTO> storageCells;
}
