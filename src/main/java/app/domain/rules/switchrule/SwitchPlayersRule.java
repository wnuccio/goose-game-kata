package app.domain.rules.switchrule;

import app.domain.player.Player;
import app.domain.player.PlayerOnTurn;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {
        this.players = players;
    }

    public void apply(PlayerOnTurn playerOnTurn) {
        List<Player> encounteredOpponents = playerOnTurn.encounteredOpponents(players);

        if (encounteredOpponents.isEmpty()) return;

        Player unluckyOpponent = encounteredOpponents.get(0);
        Position start = unluckyOpponent.position();
        Position end = playerOnTurn.previousPosition();

        unluckyOpponent.position(end);

        playerOnTurn.add(new SwitchMovement(unluckyOpponent.name(), start, end));
    }
}
