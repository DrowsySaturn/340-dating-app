package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.EventListener;


public interface EventHandler {
    public void addEvent(EventListener _event);
    public void fireEvent(EventListener _event);
    public void fireEvent();
    public void fireAllEvents();
}
