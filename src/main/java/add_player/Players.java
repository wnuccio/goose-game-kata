package add_player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private List<String> players = new ArrayList<>();

    public Players addPlayer(String playerName) {
        players.add(playerName);
        return this;
    }

    public String allNamesSeparatedByComma() {
        return String.join(", ", players);
    }

    public boolean contains(String playerName) {
        return players.contains(playerName);
    }
}
