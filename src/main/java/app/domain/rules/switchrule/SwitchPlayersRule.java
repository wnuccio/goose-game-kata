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

    public void apply(PlayerOnTurn turn) {
        List<String> encounteredOpponents = turn.encounteredOpponents(players);

        if (encounteredOpponents.isEmpty()) return;

        String unluckyOpponentName = encounteredOpponents.get(0);
        Player unluckyOpponent = players.findByName(unluckyOpponentName);
        Position unluckyOpponentPosition = unluckyOpponent.position();
        Position playerOnTurnPreviousPosition = turn.previousPosition();

        SwitchMovement switchMovement = new SwitchMovement(
                unluckyOpponentName,
                unluckyOpponentPosition,
                playerOnTurnPreviousPosition
        );

        turn.add(switchMovement);
        unluckyOpponent.position(switchMovement.finalPosition());
    }
}
