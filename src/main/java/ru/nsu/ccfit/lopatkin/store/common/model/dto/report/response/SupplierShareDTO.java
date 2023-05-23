package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;

/**
 * Доля товара конкретного поставщика в процентах, деньгах,
 * единицах от всего оборота магазина.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierShareDTO {

    private SupplierDTO supplier;

    private Double percentShare;

    private Double moneyShare;

    private Double productUnitsShare;
}
