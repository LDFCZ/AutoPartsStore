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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.StorageDTO;

/**
 * Контроллер для работы с хранилищами
 */
@Slf4j
//@RestController
//@RequestMapping("/processing/storages")
public class StorageController {

    @GetMapping("/all-storages")
    public ResponseEntity<?> getStorages() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStorage(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createStorage(@RequestBody StorageDTO storageDTO) {
        return null;
    }
}
