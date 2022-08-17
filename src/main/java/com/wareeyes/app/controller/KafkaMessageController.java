package com.wareeyes.app.controller;

import com.wareeyes.app.database.topic.TopicDriverDB;
import com.wareeyes.app.entity.KafkaMessage;
import com.wareeyes.app.entity.Topic;
import com.wareeyes.app.kafka.KafkaConsumerService;
import com.wareeyes.app.kafka.KafkaProducerService;
import com.wareeyes.app.kafka.KafkaTopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    private TopicDriverDB topicDriverDB;

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageController.class);

    @GetMapping("/get")
    public List<Topic> getTopics() {
        return topicDriverDB.selectAllTopics();
    }

    @GetMapping("/getTopicList")
    public Set<String> getTopicList() {
        return kafkaTopicService.listTopics();
    }

    @GetMapping("/getThreshold/{topic}")
    public int getTopicInfo(@PathVariable("topic") String topicName) {
        int result = topicDriverDB.getTopicThreshold(new Topic(topicName));
        LOGGER.info(topicName + " was requested for threshold data of " + result);
        return result;
    }

    @PostMapping("/createTopic")
    public boolean createTopic(@RequestBody Topic topic) {
        int insertToSQL = topicDriverDB.insertTopic(topic);
        boolean createKafkaTopic = kafkaTopicService.createTopic(topic.getName(), topic.getThreshold(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
        boolean createKafkaConsumer = kafkaConsumerService.createConsumer(topic.getName());

        if (insertToSQL == 1 && createKafkaTopic == true && createKafkaConsumer == true) {
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/createConsumer")
    public boolean createConsumer(@RequestBody Topic topic) {
        return kafkaConsumerService.createConsumer(topic.getName());
    }

    @PostMapping("/deleteTopic")
    public boolean deleteTopic(@RequestBody List<Topic> topicList) {
        return kafkaTopicService.deleteTopic(topicList);
    }

    @PostMapping("/modifyTopic")
    public boolean modifyTopic(@RequestBody Topic topic) {
        return kafkaTopicService.modifyTopic(topic.getName(), topic.getThreshold(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
    }

    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody KafkaMessage msg) {
        kafkaProducerService.sendMessage(msg);
        System.out.println("Message sent: " + msg);
        return ResponseEntity.ok("Kafka message sent to topic - " + msg);
    }
}
