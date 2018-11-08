package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;
import com.datingapp.eventsinterfaces.events.IsMatchedEvent;

import java.util.LinkedList;
import java.util.Queue;

public class IsMatchedEventHandler implements EventHandler {
    private static IsMatchedEventHandler instance = null;

    public static IsMatchedEventHandler getInstance() {
        if(instance == null) {
            IsMatchedEventHandler.instance = new IsMatchedEventHandler();
        }
        return IsMatchedEventHandler.instance;
    }

    private Queue<Event> events = new LinkedList<>();

    @Override
    public void addEvent(Event _event) {
        if(_event instanceof IsMatchedEvent) {
            events.add(_event);
        } else {
            try {
                throw new Exception("Wrong event");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void fireAllEvents() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("Empty is matched events");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            while(!this.events.isEmpty()) {
                Event event = this.events.remove();
                event.fireEvent();
            }
        }
    }


    @Override
    public void fireEvent(Event _event) {
        if(this.events.isEmpty()) {
            try{
                throw new Exception("The is matched events queue is empty");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            if(_event instanceof IsMatchedEvent) {
                this.events.remove(_event);
                _event.fireEvent();
            } else {
                try{
                    throw new Exception("Wrong event (req. IsMatchedEvent)");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void fireEvent() {
        if(this.events.isEmpty()) {
            try {
                throw new Exception("The is matched events queue is empty");
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            Event event = this.events.remove();
            event.fireEvent();
        }
    }
}
