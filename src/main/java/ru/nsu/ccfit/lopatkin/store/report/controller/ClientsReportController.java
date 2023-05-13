package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.request.ClientReportDTO;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports/clients")
public class ClientsReportController {

    /**
     * Получить перечень и общее число покупателей, купивших указанный вид
     * товара за некоторый период, либо сделавших покупку товара в объеме, не менее указанного.
     *
     * @param clientReportDTO {@link ClientReportDTO}
     * @return Если все хорошо, то вернется список клиентов {@link ClientDTO}.
     */
    @PostMapping("/by-product-and-period-or-product-count")
    public List<ClientDTO> getClientListByProductAndPeriodOrByProductCount(@RequestBody ClientReportDTO clientReportDTO) {
        return null;
    }
}
