package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Сколько пустых ячеек на складе и сколько он сможет вместить
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StorageReportDTO {

    private Integer freeCellsCount;

    private Integer freeSpaceAmount;

}
