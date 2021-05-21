package app.domain.movement;

import app.domain.movement.rules.bouncing.BouncingRule;
import app.domain.movement.rules.bridge.JumpOnBridgeRule;
import app.domain.movement.rules.first.FirstMovementRule;
import app.domain.movement.rules.goose.GooseRule;
import app.domain.movement.rules.switchrule.SwitchPlayersRule;
import app.domain.player.Players;

import java.util.LinkedList;
import java.util.List;

public class RuleProcessor {
    private final Players players;

    public RuleProcessor(Players players) {
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
