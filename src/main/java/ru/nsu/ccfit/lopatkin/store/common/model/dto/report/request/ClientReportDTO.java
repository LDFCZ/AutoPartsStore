package ru.nsu.ccfit.lopatkin.store.common.model.dto.report.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nsu.ccfit.lopatkin.store.report.controller.ClientsReportController;

import java.time.LocalDate;

/**
 * ДТО с необходимыми данными для получения отчета {@link ClientsReportController#getClientListByProductAndPeriodOrByProductCount(ClientReportDTO)}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientReportDTO {

    @NotNull
    private Long productId;

    @NotNull
    private LocalDate periodStart;

    @NotNull
    private LocalDate periodEnd;

    @Positive
    private Long count;
}
