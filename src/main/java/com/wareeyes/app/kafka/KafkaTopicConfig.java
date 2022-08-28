package com.wareeyes.app.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

// Kafka topic config class is needed to create new kafka topics
@Configuration
public class KafkaTopicConfig {

    // an admin class is needed to enable the creation of new Kafka topics
    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return new KafkaAdmin(configs);
    }

    // each default Kafka topic will be created using TopicBuilder function
    @Bean
    public NewTopic createInventoryQuantityTopic() {
        return TopicBuilder.name("Inventory-Quantity")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createNumberOfEmployeesTopic() {
        return TopicBuilder.name("Current-Number-Of-Employees")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createTransactionsCompletedTopic() {
        return TopicBuilder.name("Transactions-Completed")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createDeliverySentTopic() {
        return TopicBuilder.name("Deliveries-Sent")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createDeliveryReceivedTopic() {
        return TopicBuilder.name("Deliveries-Received")
                .partitions(2)
                .replicas(1)
                .build();
    }
}
