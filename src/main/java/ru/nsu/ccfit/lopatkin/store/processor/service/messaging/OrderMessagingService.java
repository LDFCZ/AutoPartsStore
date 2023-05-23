package ru.nsu.ccfit.lopatkin.store.processor.service.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.lopatkin.store.common.model.dto.ClientOrderDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderMessagingService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.order}")
    private String orderExchange;

    public void sendOrderForProcessing(ClientOrderDTO clientOrderDTO) {
        try {
            rabbitTemplate.convertAndSend(orderExchange, "", clientOrderDTO);
        } catch (Exception e) {
            log.error("Не удалось отправить сообщение с незаконченным заказом в RabbitMQ", e);
        }
    }
}
