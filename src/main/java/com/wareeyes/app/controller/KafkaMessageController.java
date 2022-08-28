package com.wareeyes.app.controller;

import com.wareeyes.app.database.data.DataDriverDB;
import com.wareeyes.app.database.topic.TopicDriverDB;
import com.wareeyes.app.entity.Data;
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

// controller class which contains all the RESTful APIs that involves Apache Kafka
@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaMessageController {

    // the service classes and database driver classes are autowired so that
    // their methods are able to be invoked through their specific class
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private KafkaTopicService kafkaTopicService;

    @Autowired
    private DataDriverDB dataDriverDB;

    @Autowired
    private TopicDriverDB topicDriverDB;

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageController.class);

    // returns a list of all current Kafka topic objects
    @GetMapping("/get")
    public List<Topic> getTopics() {
        return topicDriverDB.selectAllTopics();
    }

    // returns a set of strings of Kafka topic names
    @GetMapping("/getTopicList")
    public Set<String> getTopicList() {
        return kafkaTopicService.listTopics();
    }

    // returns the threshold value of requested Kafka topic
    @GetMapping("/getThreshold/{topic}")
    public int getTopicInfo(@PathVariable("topic") String topicName) {
        int result = topicDriverDB.getTopicThreshold(new Topic(topicName));
        LOGGER.info(topicName + " was requested for threshold data of " + result);
        return result;
    }

    // creates a topic in the Kafka server for a requested Kafka topic object
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

    // creates a Kafka consumer for a requested Kafka topic object
    @PostMapping("/createConsumer")
    public boolean createConsumer(@RequestBody Topic topic) {
        return kafkaConsumerService.createConsumer(topic.getName());
    }

    // deletes specific Kafka topics from a requested list of topics
    @PostMapping("/deleteTopic")
    public boolean deleteTopic(@RequestBody List<Topic> topicList) {
        return kafkaTopicService.deleteTopic(topicList);
    }

    // modifies a current Kafka topic with new variables from a requested Kafka topic object
    @PostMapping("/modifyTopic")
    public boolean modifyTopic(@RequestBody Topic topic) {
        return kafkaTopicService.modifyTopic(topic.getName(), topic.getThreshold(), (int) topic.getPartitions(), (short) topic.getReplicationFactor());
    }

    // returns a data object from the MySQL server
    @GetMapping("/requestData/{data}")
    public Data requestData(@PathVariable("data") int data) {
        return dataDriverDB.getData(data);
    }

    // publishes a Kafka message to a specific Kafka topic
    @PostMapping("/publish")
    public ResponseEntity publish(@RequestBody KafkaMessage msg) {
        kafkaProducerService.sendMessage(msg);
        System.out.println("Message sent: " + msg);
        return ResponseEntity.ok("Kafka message sent to topic - " + msg);
    }
}
