package goosegame.usecase.move_player;

import goosegame.domain.Players;

import java.util.LinkedList;
import java.util.List;

public class ComputeMovement {
    private final Players players;

    public ComputeMovement(Players players) {
        this.players = players;
    }

    public List<Movement> fromCommand(MoveCommand command) {
        LinkedList<Movement> movements = new LinkedList<>();

        new FirstMovementRule(players).apply(command, movements);
        new BouncingRule(players).apply(command, movements);
        new JumpOnBridgeRule(players).apply(command, movements);
        new GooseRule(players).apply(command, movements);
        new SwitchPlayersRule(players).apply(command, movements);

        return movements;
    }
}
