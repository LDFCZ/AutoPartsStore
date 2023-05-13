package ru.nsu.ccfit.lopatkin.store.report.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.report.response.DefectProductDTO;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/reports/defects")
public class DefectReportController {

    /**
     * Получить перечень и общее количество бракованного товара,
     * пришедшего за определенный период и список поставщиков, поставивших товар.
     *
     * @param periodStart Начало периода
     * @param periodEnd Конец периода
     * @return Если все хорошо, то вернется список бракованных товаров (деталей) {@link DefectProductDTO} за период
     */
    @GetMapping("/by-period")
    public List<DefectProductDTO> getDefectProductListByPeriod(@RequestParam LocalDate periodStart,
                                                               @RequestParam LocalDate periodEnd) {
        return null;
    }
}
