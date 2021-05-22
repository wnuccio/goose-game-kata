package _1_actions.player.move.rules;

import _1_actions.player.move.rules.bouncing.BouncingRule;
import _1_actions.player.move.rules.bridge.JumpOnBridgeRule;
import _1_actions.player.move.rules.first.FirstMovementRule;
import _1_actions.player.move.rules.goose.GooseRule;
import _1_actions.player.move.rules.switchrule.SwitchPlayersRule;
import _2_domain.movement.PlayerTurn;
import _2_domain.player.Players;

public class RuleProcessor {
    private final Players players;

    public RuleProcessor(Players players) {
        this.players = players;
    }

    public void computeMovementsFor(PlayerTurn playerTurn) {
        new FirstMovementRule(players).apply(playerTurn);
        new BouncingRule(players).apply(playerTurn);
        new JumpOnBridgeRule(players).apply(playerTurn);
        new GooseRule(players).apply(playerTurn);
        new SwitchPlayersRule(players).apply(playerTurn);
    }
}
