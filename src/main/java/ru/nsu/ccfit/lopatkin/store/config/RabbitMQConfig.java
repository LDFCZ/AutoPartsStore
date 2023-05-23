package ru.nsu.ccfit.lopatkin.store.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class RabbitMQConfig {
    private static final String RABBIT_MQ_URI_PROPERTY = "rabbitmq.uri.serial.bus";

    private static final String RABBITMQ_ORDER_EXCHANGE = "rabbitmq.exchange.order";
    private static final String RABBITMQ_ORDER_QUEUE = "rabbitmq.queue.order";

    private final Environment environment;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Autowired
    public RabbitMQConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        String rabbitMqURI = environment.getProperty(RABBIT_MQ_URI_PROPERTY);
        connectionFactory.setUri(rabbitMqURI);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean("OrderExchange")
    public FanoutExchange orderExchange() {
        return new FanoutExchange(environment.getProperty(RABBITMQ_ORDER_EXCHANGE, "AP_Store.order.exchange"));
    }

    @Bean("OrderQueue")
    public Queue orderQueue() {
        return new Queue(environment.getProperty(RABBITMQ_ORDER_QUEUE, "AP_Store.order.queue"));
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, FanoutExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange);
    }
}
