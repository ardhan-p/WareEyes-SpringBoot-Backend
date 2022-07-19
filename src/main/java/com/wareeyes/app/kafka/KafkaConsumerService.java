package com.wareeyes.app.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


// TODO: add functionality for consumer to convert and send data to websocket
@Service
public class KafkaConsumerService {

    @Autowired
    SimpMessagingTemplate template;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "testTopic")
    public void consumeMessage(@Payload String msg) {
        if (isNumber(msg)) {

            template.convertAndSend("/topic/test", msg);
        }
        LOGGER.info("Message received: " + msg);
    }

    public boolean isNumber(String str) {
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
