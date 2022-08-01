package com.wareeyes.app.kafka;

import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class KafkaTopicService {

    @Autowired
    private KafkaAdmin admin;

    private AdminClient client;

    private void getAdminClient() {
        if (client == null) {
            client = AdminClient.create(admin.getConfigurationProperties());
        }
    }

    public boolean createTopic(String topicName, int partitions, short replicationFactor) {
        try {
            NewTopic topic = new NewTopic(topicName, partitions, replicationFactor);
            CreateTopicsResult createTopicsResult = client.createTopics(Collections.singleton(topic));
            System.out.println("Created new topic: " + topicName + " Partitions: " + partitions + " Replication Factor: " + replicationFactor);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            DescribeTopicsResult describeTopics = client.describeTopics(Collections.singleton(topicName));
            System.out.println("Topic already exists: " + topicName);
            client.close();
            return false;
        }
    }



    public boolean listTopics() {
        try {
            getAdminClient();
            ListTopicsResult topics = client.listTopics();
            topics.names().get().forEach(System.out::println);
            return true;
        } catch (Exception e) {
            System.err.println(e);
            client.close();
            return false;
        }
    }
}
