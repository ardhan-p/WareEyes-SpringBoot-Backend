package com.wareeyes.app.kafka;

import com.wareeyes.app.database.topic.TopicDriverDB;
import com.wareeyes.app.entity.Topic;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaTopicService {

    @Autowired
    private KafkaAdmin admin;

    @Autowired
    private KafkaConsumerService consumerService;

    private AdminClient client;

    @Autowired
    private TopicDriverDB db;

    private void getAdminClient() {
        if (client == null) {
            client = AdminClient.create(admin.getConfigurationProperties());
        }
    }

    public boolean createTopic(String topicName, int partitions, short replicationFactor, long threshold) {
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

    public boolean deleteTopic(String topicName) {
        try {
            getAdminClient();
            DeleteTopicsResult deleteTopicsResult = client.deleteTopics(Collections.singleton(topicName));
            System.out.println("Deleted topic: " + topicName);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Topic doesn't exist: " + topicName);
            client.close();
            return false;
        }
    }

    public boolean modifyTopic(String topicName, int partitions, short replicationFactor, long threshold) {
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

    public int getTopicThreshold(String topicName) {
        Topic newTopic = new Topic(topicName);
        try {
            return db.getTopicThreshold(newTopic);
        } catch (Exception e) {
            return -1;
        }
    }

    public List<Topic> getAllTopicInfo() {
        return db.selectAllTopics();
    }
}
