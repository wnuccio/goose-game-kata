package app.config;

import app.domain.player.Board;
import app.domain.rules.bridge.JumpOnBridgeRule;

import static app.domain.player.BoardBuilder.board;

public class BoardConfiguration {

    public Board buildBoard() {
        Board board = board()
                .addPosition(0).withName("Start").andNoRule()
                .addPosition(5).havingGoose()
                .addPosition(6).withName("The Bridge").andNoRule()
                .addPosition(9).havingGoose()
                .addPosition(14).havingGoose()
                .addPosition(18).havingGoose()
                .addPosition(23).havingGoose()
                .addPosition(27).havingGoose()
                .addPosition(63).andNoRule()
                .build();

        JumpOnBridgeRule jumpOnBridgeRule = new JumpOnBridgeRule(board.position(12));
        board.position(6).attachRule(jumpOnBridgeRule);

        return board;
    }

}
