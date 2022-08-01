package com.wareeyes.app.controller;

import com.wareeyes.app.entity.Topic;
import com.wareeyes.app.kafka.KafkaProducerService;
import com.wareeyes.app.entity.User;
import com.wareeyes.app.kafka.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaTopicService kafkaTopicService;

    @GetMapping("/get")
    public boolean getTopics() {
        return kafkaTopicService.listTopics();
    }

    @PostMapping("/createTopic")
    public boolean createTopic(@RequestBody Topic topic) {
        return kafkaTopicService.createTopic(topic.getName(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
    }
    @PostMapping("/deleteTopic")
    public boolean deleteTopic(@RequestBody Topic topic) {
        return kafkaTopicService.deleteTopic(topic.getName());
    }

    @PostMapping("/modifyTopic")
    public boolean modifyTopic(@RequestBody Topic topic) {
        return kafkaTopicService.modifyTopic(topic.getName(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
    }

    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody Object obj) {
        kafkaProducerService.sendMessage(obj);
        return ResponseEntity.ok("Kafka message sent to topic");
    }
}
