package player;

import java.util.HashMap;
import java.util.Map;

public class Players {
    private Map<String, Integer> players = new HashMap<>();

    public Players addPlayer(String playerName) {
        players.put(playerName, 0);
        return this;
    }

    public String allNamesSeparatedByComma() {
        return String.join(", ", players.keySet());
    }

    public boolean contains(String playerName) {
        return players.containsKey(playerName);
    }

    public int positionOf(String playerName) {
        return players.get(playerName);
    }

    public int moveOnRoll(String playerName, int firstDice, int secondDice) {
        int totalRoll = firstDice + secondDice;
        int newPosition = positionOf(playerName) + totalRoll;
        players.put(playerName, newPosition);
        return newPosition;
    }

    public void clear() {
        players.clear();
    }
}
