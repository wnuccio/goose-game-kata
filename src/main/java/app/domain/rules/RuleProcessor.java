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
        new FirstMovementRule(players).apply(playerTurn);
        new BouncingRule(board, players).apply(playerTurn);
        new JumpOnBridgeRule(board, players).apply(playerTurn);
        new GooseRule(players).apply(playerTurn);
        new SwitchPlayersRule(players).apply(playerTurn);
    }
}
