package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.datapersistence.Match;

public class IsMatchedEvent implements Event {
    private Match match;

    public IsMatchedEvent(Match _match) {
        this.match = _match;
    }

    @Override
    public void fireEvent() {
        System.out.println(String.format("%s matched with %s", this.match.getFirstProfile().getName(), this.match.getSecondProfile().getName()));
    }

    @Override
    public T getName() {
        return "Match Event";
    }
}
