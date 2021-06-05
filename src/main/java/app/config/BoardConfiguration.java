package app.config;

import app.domain.player.Board;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;

import static app.domain.player.BoardBuilder.board;
import static app.domain.player.PositionRule.NO_RULE;

public class BoardConfiguration {

    public Board buildBoard() {
        Board board = board()
                .addPosition(0).withName("Start").andRule(NO_RULE)
                .addPosition(5).withName("5, The Goose.").andRule(new GooseRule())
                .addPosition(6).withName("The Bridge").andRule(NO_RULE)
                .addPosition(9).withName("9, The Goose.").andRule(new GooseRule())
                .addPosition(14).withName("14, The Goose.").andRule(new GooseRule())
                .addPosition(18).withName("18, The Goose.").andRule(new GooseRule())
                .addPosition(23).withName("23, The Goose.").andRule(new GooseRule())
                .addPosition(27).withName("27, The Goose.").andRule(new GooseRule())
                .addPosition(63).andRule(NO_RULE)
                .build();

        JumpOnBridgeRule jumpOnBridgeRule = new JumpOnBridgeRule(board.position(12));
        board.position(6).attachRule(jumpOnBridgeRule);

        return board;
    }

}
