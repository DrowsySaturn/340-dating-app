package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.Match;

public class HasMatchedEvent implements Event {
    private Match match;


    public HasMatchedEvent(Match _match) {
        this.match = _match;
    }


    @Override
    public void fireEvent() {
        System.out.println(String.format("%s has a match with %s", this.match.getFirstProfile().getName(), this.match.getSecondProfile().getName()));
    }


    @Override
    public String getName() {
        return "This is a has matched event";
    }
}
