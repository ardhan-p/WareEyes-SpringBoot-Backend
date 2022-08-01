package com.wareeyes.app.kafka;

import com.wareeyes.app.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Object obj) {
        Message<Object> message = MessageBuilder
                .withPayload(obj)
                .setHeader(KafkaHeaders.TOPIC, "testTopic")
                .build();

        LOGGER.info("Message sent: " + obj.toString());
        kafkaTemplate.send(message);
    }
}
