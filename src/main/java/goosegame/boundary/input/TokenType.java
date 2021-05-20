package goosegame.boundary.input;

public enum TokenType {
    ADD_PLAYER("add player"),
    PLAYER_NAME("\\w*");

    private final String regExp;

    TokenType(String regExp) {
        this.regExp = regExp;
    }

    public String regExp() {
        return regExp;
    }
}
