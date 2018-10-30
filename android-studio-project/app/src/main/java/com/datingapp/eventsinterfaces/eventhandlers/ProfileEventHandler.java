package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.EventListener;

import java.util.LinkedList;
import java.util.Queue;

public class ProfileEventHandler implements EventHandler {

    private static ProfileEventHandler instance = null;


    public static ProfileEventHandler getInstance() {
        if(ProfileEventHandler.instance == null) {
            return ProfileEventHandler.instance = new ProfileEventHandler();
        } else {
            return ProfileEventHandler.instance;
        }
    }


    private Queue<EventListener> events = new LinkedList<>();


    @Override
    public void addEvent(EventListener _event) {
        this.events.add(_event);
    }


    @Override
    public void fireEvent(EventListener _event) {
        _event.fireEvent();
        events.remove(_event);
    }


    @Override
    public void fireEvent() {
        EventListener event = events.remove();
        event.fireEvent();
    }


    @Override
    public void fireAllEvents() {
        if(events.isEmpty()) {
            System.out.println("The profile events is empty");
        } else {
            while(!events.isEmpty()) {
                EventListener event = events.remove();
                event.fireEvent();
            }
        }
    }
}
