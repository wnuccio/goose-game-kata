package _2_domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final Map<String, Position> players = new HashMap<>();

    public Players addPlayer(String playerName) {
        players.put(playerName, Position.START);
        return this;
    }

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

    public String[] all() {
        Set<String> nameSet = players.keySet();
        String[] stringArray = new String[nameSet.size()];
        return nameSet.toArray(stringArray);
    }

    public List<String> playersOnSamePositionOf(String thisPlayer) {
        List<String> result = players.keySet().stream()
                .filter(aPlayer -> positionOf(aPlayer).equals(positionOf(thisPlayer)))
                .collect(Collectors.toList());

        result.remove(thisPlayer);
        return result;
    }
}
