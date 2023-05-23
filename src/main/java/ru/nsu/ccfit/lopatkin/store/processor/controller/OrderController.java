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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;

/**
 *  Контроллер для работы с заказами клиентов
 */
@Slf4j
@RestController
@RequestMapping("/processing/orders")
public class OrderController {

    @GetMapping("/all-orders")
    public ResponseEntity<?> getOrders() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id) {
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createOrder(@RequestBody ClientOrderDTO clientOrderDTO) {
        return null;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody ClientOrderDTO clientOrderDTO) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        return null;
    }
}
