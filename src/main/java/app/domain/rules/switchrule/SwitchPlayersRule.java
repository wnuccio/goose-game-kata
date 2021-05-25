package app.domain.rules.switchrule;

import app.domain.movement.PlayerTurn;
import app.domain.player.Players;
import app.domain.player.Position;

import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {

        this.players = players;
    }

    public void apply(PlayerTurn turn) {
        List<String> encounteredPlayers = players.playersOnSamePositionOf(turn.player());

        if (encounteredPlayers.isEmpty()) return;

        String unluckyPlayer = encounteredPlayers.get(0);
        Position unluckyPlayerPosition = players.positionOf(unluckyPlayer);
        Position otherPlayerPreviousPosition = turn.previousPosition();

        SwitchMovement switchMovement = new SwitchMovement(
                unluckyPlayer,
                unluckyPlayerPosition,
                otherPlayerPreviousPosition
        );

        turn.add(switchMovement);
        players.setPositionOf(unluckyPlayer, switchMovement.finalPosition());
    }
}
