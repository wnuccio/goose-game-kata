package app.domain.player;

public class BoardForTests {

    public static Board boardEndingOn(int value) {
        return BoardBuilder.board()
                .addPosition(value)
                .andNoRule()
                .build();
    }

    public static Board standardBoard() {
        return boardEndingOn(63);
    }
}
