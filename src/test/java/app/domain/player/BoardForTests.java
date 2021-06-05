package app.domain.player;

public class BoardForTests {
    public static Board standardBoard() {
        return BoardBuilder.board()
                .withPosition(63)
                .build();
    }
}
