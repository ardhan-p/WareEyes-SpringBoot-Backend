package com.wareeyes.app.kafka;

import com.wareeyes.app.userlogin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private KafkaTemplate<String, User> kafkaTemplate;

    @KafkaListener(topics = "testTopic", groupId = "myGroup")
    public void consumeMessage(User user) {
        LOGGER.info("Message received: " + user.toString());
    }
}