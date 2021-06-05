package app.domain.player;

import static app.domain.player.PositionRule.NO_RULE;

public class BoardForTests {

    public static Board boardEndingOn(int value) {
        return BoardBuilder.board()
                .addPosition(value)
                .andRule(NO_RULE)
                .build();
    }

    public static Board standardBoard() {
        return boardEndingOn(63);
    }
}
