package com.datingapp.eventsinterfaces.eventhandlers;

import com.datingapp.eventsinterfaces.events.Event;

import java.util.ArrayList;


public interface EventHandler<T> {
    public void addEvent(Event _event);
    public T fireEvent(Event _event);
    public T fireEvent();
    public ArrayList<T> fireAllEvents();
}
