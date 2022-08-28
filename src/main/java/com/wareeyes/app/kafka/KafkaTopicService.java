package com.wareeyes.app.kafka;

import com.wareeyes.app.database.topic.TopicDriverDB;
import com.wareeyes.app.entity.Topic;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.*;

// service class for Kafka topics
@Service
public class KafkaTopicService {

    @Autowired
    private KafkaAdmin admin;

    @Autowired
    private KafkaConsumerService consumerService;

    // admin client object is needed for handling Kafka topics in Kafka server
    private AdminClient client;

    @Autowired
    private TopicDriverDB db;

    // initialises a Kafka admin if it has not done so
    private void getAdminClient() {
        if (client == null) {
            client = AdminClient.create(admin.getConfigurationProperties());
        }
    }

    // creates a new custom topic with new variables
    // it will first create the topic into the Kafka server and initialise it so that Kafka can recognise it
    // it will then insert the topic parameters onto the MySQL database
    public boolean createTopic(String topicName, long threshold, int partitions, short replicationFactor) {
        try {
            getAdminClient();
            NewTopic topic = new NewTopic(topicName, partitions, replicationFactor);
            CreateTopicsResult createTopicsResult = client.createTopics(Collections.singleton(topic));
            db.insertTopic(new Topic(topicName, partitions, replicationFactor, threshold));
            System.out.println("Created new topic: " + topicName + " Partitions: " + partitions + " Replication Factor: " + replicationFactor + " Threshold: " + threshold);
            consumerService.createConsumer(topicName);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            DescribeTopicsResult describeTopics = client.describeTopics(Collections.singleton(topicName));
            System.out.println("Topic already exists: " + topicName);
            client.close();
            return false;
        }
    }

    // deletes a list of Kafka topics
    // it will first let the Kafka admin delete each topic sequentially in the Kafka server
    // it will then delete the specific Kafka topics from the MySQL database
    public boolean deleteTopic(List<Topic> topicList) {
        try {
            getAdminClient();
            for (Topic topic : topicList) {
                DeleteTopicsResult deleteTopicsResult = client.deleteTopics(Collections.singleton(topic.getName()));
                db.deleteTopic(topic);
                System.out.println("Deleted topic: " + topic.getName());
            }
            return true;
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Selected topics does not exist!");
            client.close();
            return false;
        }
    }

    // modifies a specific Kafka topic
    // it will first let the Kafka admin modify the topic in the Kafka server with new values
    // it will then update the specific Kafka topic in the MySQL database
    public boolean modifyTopic(String topicName, long threshold, int partitions, short replicationFactor) {
        try {
            getAdminClient();
            NewTopic topic = new NewTopic(topicName, partitions, replicationFactor);
            admin.createOrModifyTopics(topic);
            db.modifyTopic(new Topic(topicName, threshold, partitions, replicationFactor));
            System.out.println("Modified topic: " + topicName + " Partitions: " + partitions + " Replication Factor: " + replicationFactor);
            return true;
        } catch (Exception e){
            System.err.println(e);
            client.close();
            return false;
        }
    }

    // list the available Kafka topics in the Kafka server
    public Set<String> listTopics() {
        try {
            getAdminClient();
            ListTopicsResult topics = client.listTopics();
            System.out.println(topics.names().get());
            return topics.names().get();
        } catch (Exception e) {
            System.err.println(e);
            client.close();
            return null;
        }
    }
}
