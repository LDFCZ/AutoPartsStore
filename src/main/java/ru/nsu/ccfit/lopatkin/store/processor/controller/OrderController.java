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
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ShopOrderDTO;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.SupplierDTO;
import ru.nsu.ccfit.lopatkin.store.processor.service.OrderService;

import java.util.List;

/**
 *  Контроллер для работы с заказами клиентов
 */
@Slf4j
@RestController
@RequestMapping("/processing/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/client/all-orders-page")
    public Page<ClientOrderDTO> getClientOrdersPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return orderService.getClientOrdersPage(offset, limit);
    }

    @GetMapping("/client/all-orders")
    public List<ClientOrderDTO> getClientOrders() {
        return orderService.getClientOrders();
    }

    @GetMapping("/shop/all-orders-page")
    public Page<ShopOrderDTO> getShopOrders(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(100) Integer limit) {
        return orderService.getShopOrdersPage(offset, limit);
    }


    @PostMapping("/client/new")
    public ClientOrderDTO createClientOrder(@RequestBody ClientOrderDTO clientOrderDTO) {
        return orderService.createClientOrder(clientOrderDTO);
    }

    @PostMapping("/shop/new")
    public ShopOrderDTO createShopOrder(@RequestBody ShopOrderDTO shopOrderDTO) {
        return orderService.createShopOrder(shopOrderDTO);
    }

    @PostMapping("/shop/complete/{id}")
    public void completeOrder(@PathVariable Long id, SupplierDTO supplierDTO) {
        orderService.completeShopOrder(id, supplierDTO);
    }
}
