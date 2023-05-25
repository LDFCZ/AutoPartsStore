package ru.nsu.ccfit.lopatkin.store.report.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.DefectProductDTO;
import ru.nsu.ccfit.lopatkin.store.common.repository.DefectRegisterRepository;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefectReportService {

    private final DefectRegisterRepository defectRegisterRepository;

    public List<DefectProductDTO> getDefectProductListByPeriod(LocalDate periodStart, LocalDate periodEnd) {
        return null;
    }
}
