package com.wareeyes.app.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

// kafka topic config class is needed to create new kafka topics
@Configuration
public class KafkaTopicConfig {

    public NewTopic createTopic() {
        return TopicBuilder.name("testTopic").build();
    }
}
