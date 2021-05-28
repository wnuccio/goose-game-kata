package app.domain.rules.switchrule;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Player;
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
        Position playerOnTurnPreviousPosition = playerOnTurn.previousPosition();

        SwitchMovement switchMovement = new SwitchMovement(
                unluckyOpponent.name(),
                unluckyOpponent.position(),
                playerOnTurnPreviousPosition
        );

        playerOnTurn.add(switchMovement);
        unluckyOpponent.position(playerOnTurnPreviousPosition);
    }
}