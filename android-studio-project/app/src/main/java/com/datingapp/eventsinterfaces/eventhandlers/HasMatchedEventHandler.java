package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.HasMatchedEvent;

import java.util.LinkedList;
import java.util.Queue;

public class HasMatchedEventHandler implements EventHandler {
    private static HasMatchedEventHandler instance = null;


    public static HasMatchedEventHandler getInstance() {
        if(HasMatchedEventHandler.instance == null) {
            HasMatchedEventHandler.instance = new HasMatchedEventHandler();
        }
        return HasMatchedEventHandler.instance;
    }

    private Queue<Event> events = new LinkedList<>();


    @Override
    public void addEvent(Event _event) {
        if(_event instanceof HasMatchedEvent) {
            this.events.add(_event);
        } else {
            try{
                throw new Exception("Wrong match event");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void fireAllEvents() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("Empty Has Matched Events");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            if(this.events.isEmpty()) {
                System.out.println("Empty events queue");
            } else {
                while(!this.events.isEmpty()) {
                    Event event = this.events.remove();
                    event.fireEvent();
                }
            }
        }
    }


    @Override
    public void fireEvent(Event _event) {
        if(_event instanceof HasMatchedEvent) {
            this.events.remove(_event);
            _event.fireEvent();
        } else {
            try {
                throw new Exception("Wrong has matched event");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void fireEvent() {
        if(this.events.isEmpty()) {
            Event event = this.events.remove();
            event.fireEvent();
        } else {
            try{
                throw new Exception("Empty Has Match events");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
