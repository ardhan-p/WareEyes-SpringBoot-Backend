package com.wareeyes.app.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    // bootstrap servers url
    // value is from application.properties
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // config map object to pass into a consumer factory
    // kafka consumers will have to deserialise key and value strings
    public Map<String, Object> consumerConfig() {
        HashMap<String, Object> property = new HashMap<>();
        property.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        property.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return property;
    }

    // factory to create new kafka consumers
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // listener receives all messages from all topics and partitions in a single thread
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> listenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}