package app.config;

import app.domain.player.Board;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import static app.domain.player.BoardBuilder.board;
import static app.domain.player.PositionRule.NO_RULE;

public class BoardConfiguration {

    public Board buildBoard() {
        Board board = board()
                .withPosition( 0, "Start", NO_RULE)
                .withPosition(5, "5, The Goose.", new GooseRule())
                .withPosition(6, "The Bridge")
                .withPosition(9, "9, The Goose.", new GooseRule())
                .withPosition(14, "14, The Goose.", new GooseRule())
                .withPosition(18, "18, The Goose.", new GooseRule())
                .withPosition(23, "23, The Goose.", new GooseRule())
                .withPosition(27, "27, The Goose.", new GooseRule())
                .withPosition(63, "63", NO_RULE)
                .build();

        JumpOnBridgeRule jumpOnBridgeRule = new JumpOnBridgeRule(board.position(12));
        board.position(6).attachRule(jumpOnBridgeRule);

        return board;
    }

}
