package eventsinterfaces.events;
/**
 * This class is an instance of Match event.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.shared.dataobjects.Match;

public class MatchEvent implements Event<Match> {
    //This stores
    private Match match;


    public MatchEvent(Match _match) {
        this.match = _match;
    }


    @Override
    public Match fireEvent() {
        return this.match;
    }
}
