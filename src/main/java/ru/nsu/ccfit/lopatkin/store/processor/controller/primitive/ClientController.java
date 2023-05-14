package ru.nsu.ccfit.lopatkin.store.processor.controller.primitive;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.lopatkin.store.common.exception.LogicException;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientDTO;

import jakarta.validation.Valid;
import ru.nsu.ccfit.lopatkin.store.processor.service.primitive.ClientService;

import javax.security.auth.login.LoginException;

/**
 *  Контроллер для работы с клиентами
 */
@Slf4j
@RestController
@RequestMapping("/processing/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/all-clients")
    public Page<ClientDTO> getClients(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                      @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        log.info("Запрос на получение страницы с клиернтами");
        return clientService.getPageWithClients(offset, limit);
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        log.info("Запрос на получение клиента с id={}", id);
        return clientService.getClintById(id);
    }

    @PostMapping("/new")
    public ClientDTO createClient(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("Запрос на создание нового клиента с name={} email={}", clientDTO.getName(), clientDTO.getEmail());
        return clientService.createClient(clientDTO);
    }

    @PutMapping("/update")
    public ClientDTO updateClient(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("Запрос на обновление клиента с id={}", clientDTO.getId());
        return clientService.createClient(clientDTO);
    }

    @DeleteMapping("/delete/{id}")
    public Long deleteClient(@PathVariable Long id) {
        log.info("Запрос на удаление клиента с id={}", id);
        return clientService.deleteClient(id);
    }
}
