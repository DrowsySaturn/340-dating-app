package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.EventListener;

import java.util.LinkedList;
import java.util.Queue;

public class SignUpEventHandler implements EventHandler {

    private static SignUpEventHandler instance = null;


    public static SignUpEventHandler getInstance() {
        if(instance == null) {
            return SignUpEventHandler.instance = new SignUpEventHandler();
        } else {
            return SignUpEventHandler.instance;
        }
    }


    private Queue<EventListener> events = new LinkedList<>();


    @Override
    public void addEvent(EventListener _event) {
        this.events.add(_event);
    }


    @Override
    public void fireAllEvents() {
        if(this.events.isEmpty()) {
            System.out.println("The sign up events is empty");
        } else {
            while(!events.isEmpty()) {
                EventListener event = this.events.remove();
                event.fireEvent();
            }
        }
    }


    @Override
    public void fireEvent(EventListener _event) {
        _event.fireEvent();
        this.events.remove(_event);
    }


    @Override
    public void fireEvent() {
        EventListener event = events.remove();
        event.fireEvent();
    }
}
