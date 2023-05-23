package ru.nsu.ccfit.lopatkin.store.sales.control.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;
import ru.nsu.ccfit.lopatkin.store.sales.control.service.OrderProcessingService;

// слушает заказы и создает для них заказы от магазина производителям или проводит их через кассу
@Slf4j
@Service
@RequiredArgsConstructor
@RabbitListener(queues = "${rabbitmq.queue.order}")
public class OrderListener {

    private final OrderProcessingService orderProcessingService;

    @RabbitHandler
    public void handleOrder(ClientOrderDTO clientOrderDTO) {
        if (clientOrderDTO.isCompleted()) {
            orderProcessingService.handleCompletedOrder(clientOrderDTO);
        } else {
            orderProcessingService.handleUncompletedOrder(clientOrderDTO);
        }
    }
}
