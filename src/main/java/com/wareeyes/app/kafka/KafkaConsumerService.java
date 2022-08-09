package com.wareeyes.app.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class KafkaConsumerService {
    @Autowired
    SimpMessagingTemplate template;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServer;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    private Map<LocalDateTime, Long> inventoryQuantityMap = new HashMap<>();

    @KafkaListener(topics = "Inventory-Quantity")
    public void consumeInventoryQuantityMessage(@Payload Long msg) {
        template.convertAndSend("/topic/Inventory-Quantity", msg);
        inventoryQuantityMap.put(LocalDateTime.now(), msg);

        System.out.println(inventoryQuantityMap);
        LOGGER.info("Inventory quantity update received: " + msg);
    }

    @KafkaListener(topics = "Current-Number-Of-Employees")
    public void consumeNumberEmployeesMessage(@Payload Long msg) {
        template.convertAndSend("/topic/Current-Number-Of-Employees", msg);
        LOGGER.info("Current amount of employees update received: " + msg);
    }

    @KafkaListener(topics = "Transactions-Completed")
    public void consumeTransactionsMessage(@Payload Long msg) {
        template.convertAndSend("/topic/Transactions-Completed", msg);
        LOGGER.info("Current transaction data received: " + msg);
    }

    @KafkaListener(topics = "Deliveries-Sent")
    public void consumeDeliveriesSentMessage(@Payload Long msg) {
        template.convertAndSend("/topic/Deliveries-Sent", msg);
        LOGGER.info("Deliveries sent data update received: " + msg);
    }

    @KafkaListener(topics = "Deliveries-Received")
    public void consumeDeliveriesReceivedMessage(@Payload Long msg) {
        template.convertAndSend("/topic/Deliveries-Received", msg);
        LOGGER.info("Deliveries received data update received: " + msg);
    }

    public boolean createConsumer(String topic) {
        try {
            ContainerProperties containerProps = new ContainerProperties(topic);
            String destination = "/topic/" + topic;

            containerProps.setMessageListener((MessageListener<String, Long>) message -> {
                template.convertAndSend(destination, message.value());
                LOGGER.info("Custom msg received: " + message.value() + " - Topic: " + destination);
            });

            KafkaMessageListenerContainer<String, Long> container = createContainer(containerProps);
            container.setBeanName(topic);
            container.start();
            return true;
        } catch (Exception e) {
            System.err.println("Something went wrong in creating consumer!");
            System.err.println(e);
            return false;
        }
    }

    private KafkaMessageListenerContainer<String, Long> createContainer(ContainerProperties containerProps) {
        Map<String, Object> props=new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-id");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        DefaultKafkaConsumerFactory<String, Long> cf = new DefaultKafkaConsumerFactory<>(props);
        KafkaMessageListenerContainer<String, Long> container = new KafkaMessageListenerContainer<>(cf, containerProps);
        return container;
    }

    public void createOffsetConsumer() {

    }
}
