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
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;

import jakarta.validation.Valid;

import javax.security.auth.login.LoginException;

/**
 *  Контроллер для работы с клиентами
 */
@Slf4j
@RestController
@RequestMapping("/processing/clients")
public class ClientController {

    @GetMapping("/all-clients")
    public ResponseEntity<?> getClients() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClientDTO clientDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable String id) {
        return null;
    }
}
