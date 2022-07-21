package com.wareeyes.app.controller;

import com.wareeyes.app.kafka.KafkaProducerService;
import com.wareeyes.app.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {
    private KafkaProducerService kafkaProducerService;

    public KafkaMessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    // http://localhost:8080/api/kafka/publish
    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody Object obj) {
        kafkaProducerService.sendMessage(obj);
        return ResponseEntity.ok("Kafka message sent to topic");
    }
}
