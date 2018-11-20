package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;


public interface EventHandler {
    public void addEvent(Event _event);
    public void fireEvent(Event _event);
    public void fireEvent();
    public void fireAllEvents();
}
