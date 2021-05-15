package domain;

import java.util.*;
import java.util.stream.Collectors;

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

    public List<String> playersOnSamePositionOf(String thisPlayer) {
        Integer position = positionOf(thisPlayer);
        return players.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(position) &&  ! entry.getKey().equals(thisPlayer))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }
}
