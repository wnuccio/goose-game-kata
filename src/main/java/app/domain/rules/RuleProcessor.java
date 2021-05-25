package app.domain.rules;

import app.domain.movement.PlayerTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.rules.bouncing.BouncingRule;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.first.FirstMovementRule;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final Board board;
    private final Players players;

    public RuleProcessor(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    public void computeMovementsFor(PlayerTurn playerTurn) {
        new FirstMovementRule().apply(playerTurn);
        new BouncingRule(board).apply(playerTurn);
        new JumpOnBridgeRule(board).apply(playerTurn);
        new GooseRule().apply(playerTurn);
        new SwitchPlayersRule(players).apply(playerTurn);
    }
}
