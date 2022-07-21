package com.wareeyes.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notification {
    private long id;
    private String message;
    private LocalDate date;
    private LocalTime time;

    public Notification() {
    }

    public Notification(long id, String message, LocalDate date, LocalTime time) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public Notification(long id, String message) {
        this.id = id;
        this.message = message;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public long getId() {
        return id;
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
                ", message='" + message + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
