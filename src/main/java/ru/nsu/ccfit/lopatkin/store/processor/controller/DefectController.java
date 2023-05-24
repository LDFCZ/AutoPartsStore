package ru.nsu.ccfit.lopatkin.store.processor.controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.DefectDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.DefectService;

/**
 * Контроллер для работы с гарантийными случаями
 */
@Slf4j
@RestController
@RequestMapping("/processing/defects")
@RequiredArgsConstructor
public class DefectController {

    private final DefectService defectService;

    @GetMapping("/all-defects-page")
    public Page<DefectDTO> getDefects(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return defectService.getPageWithDefects(offset, limit);
    }

    @PostMapping("/new")
    public DefectDTO createDefect(@RequestBody DefectDTO defectDTO) {
        return defectService.createDefect(defectDTO);
    }

}
