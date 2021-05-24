package _2_domain.rules;

import _2_domain.movement.PlayerTurn;
import _2_domain.player.Board;
import _2_domain.player.Players;
import _2_domain.rules.bouncing.BouncingRule;
import _2_domain.rules.bridge.JumpOnBridgeRule;
import _2_domain.rules.first.FirstMovementRule;
import _2_domain.rules.goose.GooseRule;
import _2_domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final Board board;
    private final Players players;

    public RuleProcessor(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    public void computeMovementsFor(PlayerTurn playerTurn) {
        new FirstMovementRule(players).apply(playerTurn);
        new BouncingRule(board, players).apply(playerTurn);
        new JumpOnBridgeRule(board, players).apply(playerTurn);
        new GooseRule(players).apply(playerTurn);
        new SwitchPlayersRule(players).apply(playerTurn);
    }
}
