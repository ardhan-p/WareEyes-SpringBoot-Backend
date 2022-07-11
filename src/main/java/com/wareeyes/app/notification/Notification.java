package com.wareeyes.app.notification;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notification {
    private long id;
    private long thresholdValue;
    private String dataset;
    private String message;
    private LocalDate date;
    private LocalTime time;

    public Notification() {
    }

    public Notification(long id, long thresholdValue, String dataset, String message, LocalDate date, LocalTime time) {
        this.id = id;
        this.thresholdValue = thresholdValue;
        this.dataset = dataset;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public Notification(long id, long thresholdValue, String dataset, String message) {
        this.id = id;
        this.thresholdValue = thresholdValue;
        this.dataset = dataset;
        this.message = message;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public long getId() {
        return id;
    }

    public long getThresholdValue() {
        return thresholdValue;
    }

    public String getDataset() {
        return dataset;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setThresholdValue(long thresholdValue) {
        this.thresholdValue = thresholdValue;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", thresholdValue=" + thresholdValue +
                ", dataset='" + dataset + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
