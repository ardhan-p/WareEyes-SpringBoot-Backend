package com.wareeyes.app.kafka;

import com.wareeyes.app.userlogin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


// TODO: add functionality for consumer to convert and send data to websocket
@Service
public class KafkaConsumer {

    @Autowired
    SimpMessagingTemplate template;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private KafkaTemplate<String, User> kafkaTemplate;

    @KafkaListener(topics = "testTopic", groupId = "myGroup")
    public void consumeMessage(User user) {
        LOGGER.info("Message received: " + user.toString());
    }
}
