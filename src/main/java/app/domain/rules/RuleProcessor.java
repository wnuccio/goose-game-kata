package app.domain.rules;

import app.domain.movement.PlayerOnTurn;
import app.domain.player.Board;
import app.domain.player.Players;
import app.domain.rules.bouncing.BouncingRule;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;

public class RuleProcessor {
    private final Board board;
    private final Players players;

    public RuleProcessor(Board board, Players players) {
        this.board = board;
        this.players = players;
    }

    public void computeMovementsFor(PlayerOnTurn playerOnTurn) {
        playerOnTurn.move();

        new BouncingRule(board).apply(playerOnTurn);
        new JumpOnBridgeRule(board).apply(playerOnTurn);
        new GooseRule().apply(playerOnTurn);
        new SwitchPlayersRule(players).apply(playerOnTurn);
    }
}
