package _1_actions.player.move.rules.switchrule;

import _2_domain.movement.MoveCommand;
import _2_domain.movement.Movement;
import _2_domain.movement.PresentableMovement;
import _2_domain.player.Players;
import _2_domain.player.Position;

import java.util.LinkedList;
import java.util.List;

public class SwitchPlayersRule {
    private final Players players;

    public SwitchPlayersRule(Players players) {

        this.players = players;
    }

    public void apply(MoveCommand command, LinkedList<PresentableMovement> movements) {
        Movement lastMovement = movements.getLast();
        List<String> encounteredPlayers = players.playersOnSamePositionOf(command.player());

        if (encounteredPlayers.isEmpty()) return;

        String unluckyPlayer = encounteredPlayers.get(0);

        Position unluckyPlayerPosition = players.positionOf(unluckyPlayer);
        Position otherPlayerPreviousPosition = lastMovement.startPosition();
        SwitchMovement switchMovement = new SwitchMovement(
                unluckyPlayer,
                unluckyPlayerPosition,
                otherPlayerPreviousPosition
        );
        players.setPositionOf(unluckyPlayer, otherPlayerPreviousPosition);

        movements.add(switchMovement);
    }
}
