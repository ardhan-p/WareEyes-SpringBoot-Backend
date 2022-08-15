package com.wareeyes.app.entity;

public class MessageEvent {
    private String x;
    private int y;

    public MessageEvent() {
    }

    public MessageEvent(String time, int value) {
        this.x = time;
        this.y = value;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "x='" + x + '\'' +
                ", y=" + y +
                '}';
    }
}
