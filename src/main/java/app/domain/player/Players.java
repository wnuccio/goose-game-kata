package app.domain.player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Players {
    private final Map<String, Player> players = new HashMap<>();

    public boolean contains(String playerName) {
        return players.containsKey(playerName);
    }

    public Position positionOf(String playerName) {
        if (! contains(playerName)) throw new NoSuchElementException("No such player: " + playerName);

        return players.get(playerName).position();
    }

    public void setPositionOf(String playerName, Position position) {
        players.put(playerName, new Player(playerName).position(position));
    }

    public void clear() {
        players.clear();
    }

    public Set<String> all() {
        return players.keySet();
    }

}
