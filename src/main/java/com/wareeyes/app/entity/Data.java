package com.wareeyes.app.entity;

public class Data {
    private int id;
    private int data;

    public Data() {
    }

    public Data(int id) {
        this.id = id;
    }

    public Data(int id, int data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", data=" + data +
                '}';
    }
}
