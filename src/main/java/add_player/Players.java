package add_player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private List<String> players = new ArrayList<>();

    public void addPlayer(String playerName) {
        players.add(playerName);
    }

    public List<String> all() {
        return Collections.unmodifiableList(players);
    }
}
