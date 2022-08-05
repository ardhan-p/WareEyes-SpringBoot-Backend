package com.wareeyes.app.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


// TODO: add functionality for consumer to convert and send data to websocket
@Service
public class KafkaConsumerService {

    @Autowired
    SimpMessagingTemplate template;

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServer;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);


//    @KafkaListener(topics = "testTopic")
//    public void consumeMessage(@Payload String msg) {
//        if (isNumber(msg)) {
//
//            template.convertAndSend("/topic/testTheTopic", msg);
//        }
//        LOGGER.info("Message received: " + msg);
//    }

    public boolean createConsumer(String topic) {
        try {
            ContainerProperties containerProps = new ContainerProperties(topic);
            String destination = "/topic/" + topic;

            containerProps.setMessageListener((MessageListener<String, String>) message -> {
                if (isNumber(message.value())) {
                    template.convertAndSend(destination, message.value());
                }
                LOGGER.info("Custom msg received: " + message.value() + " - Topic: " + destination);
            });

            KafkaMessageListenerContainer<String, String> container = createContainer(containerProps);
            container.setBeanName(topic);
            container.start();
            return true;
        } catch (Exception e) {
            System.err.println("Something went wrong in creating consumer!");
            System.err.println(e);
            return false;
        }
    }

    private KafkaMessageListenerContainer<String, String> createContainer(ContainerProperties containerProps) {
        Map<String, Object> props=new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-id");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(props);
        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(cf, containerProps);
        return container;
    }

    public boolean isNumber(String str) {
        try {
            @SuppressWarnings("unused")
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
