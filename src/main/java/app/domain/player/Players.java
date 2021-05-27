package app.domain.player;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private final Map<String, Player> players = new HashMap<>();

    public Players() {
    }

    public boolean contains(String playerName) {
        return players.containsKey(playerName);
    }

    public void add(Player player) {
        players.put(player.name(), player);
    }

    public Player find(String playerName) {
        if (! contains(playerName)) throw new NoSuchElementException("No such player: " + playerName);

        return players.get(playerName);
    }

    public void clear() {
        players.clear();
    }

    public Set<String> allNames() {
        return players.keySet();
    }

    public Set<Player> all() {
        return new HashSet<>(players.values());
    }

    public List<Player> opponentsOnSamePositionOf(Player player) {
        return all()
                .stream()
                .filter(aPlayer -> aPlayer.position().equals(player.position()))
                .filter(aPlayer -> aPlayer != player)
                .collect(Collectors.toList());
    }
}
