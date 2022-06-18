package com.wareeyes.app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    // this function will listen to the specified topic
    @KafkaListener(
            topics = "testTopic",
            groupId = "listener1"
    )
    void listener(String data) {
        System.out.println("Listener received: " + data);
    }
}
