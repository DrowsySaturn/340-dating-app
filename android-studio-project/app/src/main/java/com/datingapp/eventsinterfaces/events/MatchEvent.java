package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.Match;

public class MatchEvent implements Event<Match> {
    private Match match;


    public MatchEvent(Match _match) {
        this.match = _match;
    }


    @Override
    public Match fireEvent() {
        return this.match;
    }
}
