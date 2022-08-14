package com.wareeyes.app.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Notification {
    private long id;
    private String message;
    private String date;
    private String time;

    public Notification() {
    }

    public Notification(long id, String message, String date, String time) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public Notification(long id, String message) {
        this.id = id;
        this.message = message;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().toString();
    }

    public Notification(String message, String date) {
        this.message = message;
        this.date = date;
    }

    public Notification(String message, String date, String time) {
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
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
