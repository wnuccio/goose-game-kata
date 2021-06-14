package app.domain.rules.switchrule;

import app.domain.movement.MovementsRecorder;
import app.domain.player.Player;
import app.domain.player.Players;

import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {
        this.players = players;
    }

    public void apply(Player player, MovementsRecorder movementsRecorder) {
        List<Player> encounteredOpponents = players.opponentsOnSamePositionOf(player);

        if (encounteredOpponents.isEmpty()) return;

        Player unluckyOpponent = encounteredOpponents.get(0);

        moveBack(unluckyOpponent, movementsRecorder);
    }

    private void moveBack(Player unluckyOpponent, MovementsRecorder movementsRecorder) {
        unluckyOpponent.addObserver(movementsRecorder);

        SwitchMovement movement = new SwitchMovement(
                unluckyOpponent.name(),
                unluckyOpponent.position(),
                movementsRecorder.penultimatePosition());

        unluckyOpponent.applyMovement(movement);
    }
}
