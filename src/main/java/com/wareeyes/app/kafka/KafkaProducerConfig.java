package com.wareeyes.app.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

// configuration class for Kafka producers
@EnableKafka
@Configuration
public class KafkaProducerConfig {

    // uses the Kafka server URL from application properties
    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServer;

    // all configuration values are placed in a map
    // the key and value will need to be serialized into a string and long values respectively
    // this is to ensure that the producers are able to format the data correctly
    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props=new HashMap<String, Object>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        return props;
    }

    // producer factory is responsible for the creation of
    // kafka producers with the specific configuration
    @Bean
    public ProducerFactory<String, Long> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    // Kafka template ensures that each message conforms to the specific format
    @Bean
    public KafkaTemplate<String, Long> kafkaTemplate(ProducerFactory<String, Long> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
