package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageCellDTO;

/**
 * Контроллер для работы с ячейками хранения
 */
@Slf4j
@RestController
@RequestMapping("/processing/storage-cells")
public class StorageCellController {

    @GetMapping("/all-storage-cells")
    public ResponseEntity<?> getStorageCells() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStorageCell(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createStorageCell(@RequestBody StorageCellDTO storageCellDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateStorageCell(@RequestBody StorageCellDTO storageCellDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStorageCell(@PathVariable String id) {
        return null;
    }
}
