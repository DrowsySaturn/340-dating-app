package com.datingapp.event;

import java.util.LinkedList;
import java.util.Queue;

public class EventHandler {
    private static Queue<EventListener> events = new LinkedList<>();

    public static void addEvent(EventListener _event) {
        EventHandler.events.add(_event);
        System.out.println(String.format("Event: %s acquired", _event.getName()));
    }


    public static void fireTopEvent() {
        EventHandler.events.element().fireEvent();
        EventHandler.events.remove();
    }


    public static void fireEvent(EventListener _event) {
        _event.fireEvent();
        EventHandler.events.remove(_event);
    }

    public static void fireAllEvent() {
        if(EventHandler.events.isEmpty()) {
            System.out.println("Currently No Event");
        } else {
            for(EventListener event : EventHandler.events) {
                event.fireEvent();
                EventHandler.events.remove(event);
            }
        }
    }
}
