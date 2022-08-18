package com.henryxi.eventlistener;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener
    public void myEvent(PayloadApplicationEvent<MyEvent> myEvent) {
        System.out.println("my event:" + myEvent.toString());
    }
}
