package app.domain.player;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Players {
    private final Map<String, Position> players = new HashMap<>();

    public boolean contains(String playerName) {
        return players.containsKey(playerName);
    }

    public Position positionOf(String playerName) {
        if (! contains(playerName)) throw new NoSuchElementException("No such player: " + playerName);

        return players.get(playerName);
    }

    public void setPositionOf(String player, Position position) {
        players.put(player, position);
    }

    public void clear() {
        players.clear();
    }

    public Set<String> all() {
        return players.keySet();
    }

}
