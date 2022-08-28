package com.wareeyes.app.entity;

// KafkaMessage class that represents a published Kafka data event
// each message will have a specific topic attached to it
// as well as the value the message will hold
public class KafkaMessage {
    private String topic;
    private long value;

    public KafkaMessage() {
    }
    public KafkaMessage(String topic, long value) {
        this.topic = topic;
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "KafkaMessage{" +
                "topic='" + topic + '\'' +
                ", value=" + value +
                '}';
    }
}
