package _1_actions.player.move.rules;

import _1_actions.player.move.presenter.PresentableMovement;
import _1_actions.player.move.rules.bouncing.BouncingRule;
import _1_actions.player.move.rules.bridge.JumpOnBridgeRule;
import _1_actions.player.move.rules.first.FirstMovementRule;
import _1_actions.player.move.rules.goose.GooseRule;
import _1_actions.player.move.rules.switchrule.SwitchPlayersRule;
import _2_domain.MoveCommand;
import _2_domain.Players;

import java.util.LinkedList;
import java.util.List;

public class RuleProcessor {
    private final Players players;

    public RuleProcessor(Players players) {
        this.players = players;
    }

    public List<PresentableMovement> fromCommand(MoveCommand command) {
        LinkedList<PresentableMovement> movements = new LinkedList<>();

        new FirstMovementRule(players).apply(command, movements);
        new BouncingRule(players).apply(command, movements);
        new JumpOnBridgeRule(players).apply(command, movements);
        new GooseRule(players).apply(command, movements);
        new SwitchPlayersRule(players).apply(command, movements);

        return movements;
    }
}
