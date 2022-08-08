package com.wareeyes.app.controller;

import com.wareeyes.app.entity.KafkaMessage;
import com.wareeyes.app.entity.Topic;
import com.wareeyes.app.kafka.KafkaConsumerService;
import com.wareeyes.app.kafka.KafkaProducerService;
import com.wareeyes.app.kafka.KafkaTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private KafkaTopicService kafkaTopicService;

    @GetMapping("/get")
    public Set<String> getTopics() {
        return kafkaTopicService.listTopics();
    }

    // TODO: add API to get request topic information
    @GetMapping("/get/{topic}")
    public Map<String, Object> getTopicInfo() {
        return null;
    }

    @PostMapping("/createTopic")
    public boolean createTopic(@RequestBody Topic topic) {
        return kafkaTopicService.createTopic(topic.getName(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
    }

    @PostMapping("/createConsumer")
    public boolean createConsumer(@RequestBody Topic topic) {
        return kafkaConsumerService.createConsumer(topic.getName());
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
    public ResponseEntity publish(@RequestBody KafkaMessage msg) {
        kafkaProducerService.sendMessage(msg);
        System.out.println("Message sent: " + msg);
        return ResponseEntity.ok("Kafka message sent to topic - " + msg);
    }
}
