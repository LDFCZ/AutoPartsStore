package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.request.ClientReportDTO;
import ru.nsu.ccfit.lopatkin.store.report.service.ClientReportService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports/clients")
@RequiredArgsConstructor
public class ClientsReportController {

    /**
     * Получить перечень и общее число покупателей, купивших указанный вид
     * товара за некоторый период, либо сделавших покупку товара в объеме, не менее указанного.
     *
     * @param clientReportDTO {@link ClientReportDTO}
     * @return Если все хорошо, то вернется список клиентов {@link ClientDTO}.
     *
     *
     *
     * SELECT c.id, c.name, c.email
     * FROM clients c
     * JOIN client_orders co ON c.id = co.client_id
     * JOIN client_orders_products cop ON co.id = cop.order_id
     * JOIN products p ON cop.product_id = p.id
     * WHERE (p.product_type_id = <product_type_id> OR cop.count >= <min_count>)
     *   AND co.order_date >= <start_date>
     *   AND co.order_date <= <end_date>
     * GROUP BY c.id, c.name, c.email;
     */

    private final ClientReportService clientReportService;

    @PostMapping("/by-product-and-period-or-product-count")
    public List<ClientDTO> getClientListByProductAndPeriodOrByProductCount(@RequestBody ClientReportDTO clientReportDTO) {
        return clientReportService.getClientListByProductAndPeriodOrByProductCount(clientReportDTO);
    }
}
