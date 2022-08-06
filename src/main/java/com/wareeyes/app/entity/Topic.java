package com.wareeyes.app.entity;

public class Topic {
    private long id;
    private String name;
    private long threshold;
    private long partitions;
    private long replicationFactor;

    public Topic() {
    }

    public Topic(long id, String name, long threshold, long partitions, long replicationFactor) {
        this.id = id;
        this.name = name;
        this.threshold = threshold;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    public Topic(String name) {
        this.name = name;
    }

    public Topic(String name, long partitions, long replicationFactor) {
        this.name = name;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    public Topic(String name, long threshold, long partitions, long replicationFactor) {
        this.name = name;
        this.threshold = threshold;
        this.partitions = partitions;
        this.replicationFactor = replicationFactor;
    }

    public Topic(long id, String name, long threshold) {
        this.id = id;
        this.name = name;
        this.threshold = threshold;
        this.partitions = 1;
        this.replicationFactor = 1;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getThreshold() {
        return threshold;
    }

    public long getPartitions() {
        return partitions;
    }

    public long getReplicationFactor() {
        return replicationFactor;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }

    public void setPartitions(long partitions) {
        this.partitions = partitions;
    }

    public void setReplicationFactor(long replicationFactor) {
        this.replicationFactor = replicationFactor;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", threshold=" + threshold +
                ", partitions=" + partitions +
                ", replicationFactor=" + replicationFactor +
                '}';
    }
}
