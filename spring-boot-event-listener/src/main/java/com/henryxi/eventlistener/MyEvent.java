package com.henryxi.eventlistener;

import org.springframework.context.ApplicationEvent;

public class MyEvent {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public MyEvent(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "data='" + data + '\'' +
                '}';
    }
}
