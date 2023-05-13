package ru.nsu.ccfit.lopatkin.store.processor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.DefectDTO;

/**
 * Контроллер для работы с гарантийными случаями
 */
@Slf4j
@RestController
@RequestMapping("/processing/defects")
public class DefectController {

    @GetMapping("/all-defects")
    public ResponseEntity<?> getDefects() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDefect(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createDefect(@RequestBody DefectDTO defectDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDefect(@RequestBody DefectDTO defectDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDefect(@PathVariable String id) {
        return null;
    }
}
