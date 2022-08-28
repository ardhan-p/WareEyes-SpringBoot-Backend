package com.wareeyes.app.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

// configuration class for Kafka consumers
@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    // uses the Kafka server URL from application properties
    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServer;

    // all configuration values are placed in a map
    // the key and value will need to be deserialized into a string and long values respectively
    // this is to ensure that the consumers are able to consume the data correctly
    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> props=new HashMap<String, Object>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test1");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return props;
    }

    // consumer factory is responsible for the creation of
    // kafka consumers with the specific configuration
    @Bean
    public ConsumerFactory<String, Long> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    // the concurrent Kafka listener container ensures that new consumers
    // are able to be created in conjunction with other existing consumers
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Long>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
