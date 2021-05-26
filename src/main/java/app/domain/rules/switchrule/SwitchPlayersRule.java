package app.domain.rules.switchrule;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {
        this.players = players;
    }

    public void apply(PlayerOnTurn turn) {
        List<String> encounteredOpponents = turn.encounteredOpponents(players);

        if (encounteredOpponents.isEmpty()) return;

        String unluckyOpponent = encounteredOpponents.get(0);
        Position unluckyOpponentPosition = players.positionOf(unluckyOpponent);
        Position playerOnTurnPreviousPosition = turn.previousPosition();

        SwitchMovement switchMovement = new SwitchMovement(
                unluckyOpponent,
                unluckyOpponentPosition,
                playerOnTurnPreviousPosition
        );

        turn.add(switchMovement);
        players.findByName(unluckyOpponent).position(switchMovement.finalPosition());
    }
}
