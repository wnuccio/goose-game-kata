package domain;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class Players {
    private Map<String, Integer> players = new HashMap<>();

    public Players addPlayer(String playerName) {
        players.put(playerName, 0);
        return this;
    }

    public boolean contains(String playerName) {
        return players.containsKey(playerName);
    }

    public int positionOf(String playerName) {
        if (! contains(playerName)) throw new NoSuchElementException("No such player: " + playerName);

        return players.get(playerName);
    }

    public void setPositionOf(String player, int position) {
        players.put(player, position);
    }

    public void clear() {
        players.clear();
    }

    public String[] all() {
        Set<String> nameSet = players.keySet();
        String[] stringArray = new String[nameSet.size()];
        return nameSet.toArray(stringArray);
    }
}
