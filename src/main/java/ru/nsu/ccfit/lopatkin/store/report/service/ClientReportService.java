package ru.nsu.ccfit.lopatkin.store.report.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.mapper.ClientMapper;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.request.ClientReportDTO;
import ru.nsu.ccfit.lopatkin.store.common.repository.ClientRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientReportService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    public List<ClientDTO> getClientListByProductAndPeriodOrByProductCount(ClientReportDTO clientReportDTO) {
        return clientRepository.findClientsByProductOrMinPriceAndOrderDateBetween(clientReportDTO.getProductId(),
                clientReportDTO.getCount(),
                clientReportDTO.getPeriodStart(),
                clientReportDTO.getPeriodEnd()).stream().map(clientMapper::clintToClientDTO).toList();
    }
}
