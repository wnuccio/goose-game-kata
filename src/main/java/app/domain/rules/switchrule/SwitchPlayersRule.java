package app.domain.rules.switchrule;

import app.domain.movement.Movements;
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

    public void apply(PlayerOnTurn playerOnTurn, Movements movements) {
        List<Player> encounteredOpponents = playerOnTurn.opponentsOnSamePosition(players);

        if (encounteredOpponents.isEmpty()) return;

        Player unluckyOpponent = encounteredOpponents.get(0);
        Position start = unluckyOpponent.position();
        Position end = movements.penultimatePosition();

        unluckyOpponent.position(end);

        movements.add(new SwitchMovement(unluckyOpponent.name(), start, end));
    }
}
