package app.config;

import app.domain.player.Board;
import app.domain.player.BoardBuilder;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import static app.domain.player.PositionRule.NO_RULE;

public class BoardConfiguration {

    public Board buildBoard() {
        Board board = BoardBuilder
                .board()
                .sized(0, 63)
                .position( 0, "Start", NO_RULE)
                .position(5, "5, The Goose.", new GooseRule())
                .position(6, "The Bridge")
                .position(9, "9, The Goose.", new GooseRule())
                .position(14, "14, The Goose.", new GooseRule())
                .position(18, "18, The Goose.", new GooseRule())
                .position(23, "23, The Goose.", new GooseRule())
                .position(27, "27, The Goose.", new GooseRule())
                .position(63, "63", NO_RULE)
                .build();

        JumpOnBridgeRule jumpOnBridgeRule = new JumpOnBridgeRule(board.position(12));
        board.position(6).attachRule(jumpOnBridgeRule);

        return board;
    }

}
