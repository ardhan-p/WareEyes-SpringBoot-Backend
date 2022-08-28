package com.wareeyes.app.kafka;

import com.wareeyes.app.entity.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

// service class for Kafka consumers
@Service
public class KafkaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    // template is used to produce Kafka message
    private KafkaTemplate<String, Long> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Long> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // each produced message will be transformed into a Kafka template, which will be sent to consumers
    public void sendMessage(KafkaMessage msg) {
        Message<Long> message = MessageBuilder
                .withPayload(msg.getValue())
                .setHeader(KafkaHeaders.TOPIC, msg.getTopic())
                .build();

        kafkaTemplate.send(message);
        LOGGER.info("Message sent: " + msg.getValue() + ", to Kafka topic: " + msg.getTopic());
    }
}
