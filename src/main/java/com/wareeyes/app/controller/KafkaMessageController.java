package com.wareeyes.app.controller;

import com.wareeyes.app.kafka.KafkaProducer;
import com.wareeyes.app.userlogin.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {
    private KafkaProducer kafkaProducer;

    public KafkaMessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    // http://localhost:8080/api/kafka/publish?user=message
    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody User user) {
        kafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Kafka message sent to topic");
    }
}
