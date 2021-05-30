package app.domain.rules.switchrule;

import app.domain.movement.Movements;
import app.domain.player.Player;
import app.domain.player.Players;

import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {
        this.players = players;
    }

    public void apply(Player player, Movements movements) {
        List<Player> encounteredOpponents = players.opponentsOnSamePositionOf(player);

        if (encounteredOpponents.isEmpty()) return;

        Player unluckyOpponent = encounteredOpponents.get(0);

        moveBack(unluckyOpponent, movements);
    }

    private void moveBack(Player unluckyOpponent, Movements movements) {
        unluckyOpponent.addObserver(movements);

        SwitchMovement movement = new SwitchMovement(
                unluckyOpponent.name(),
                unluckyOpponent.position(),
                movements.penultimatePosition());

        unluckyOpponent.applyMovement(movement);
    }
}
