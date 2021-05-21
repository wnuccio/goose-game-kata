package goosegame.lexer;

public enum TokenType {
    ADD_PLAYER("add player"),
    PLAYER_NAME("\\w*"),
    MOVE_PLAYER("move"),
    DICE("[1-6]"),
    RESET_GAME("reset game"),
    STOP_GAME("stop game");

    private final String regExp;

    TokenType(String regExp) {
        this.regExp = regExp;
    }

    public String regex() {
        return regExp;
    }
}
