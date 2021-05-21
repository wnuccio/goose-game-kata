package goosegame.lexer;

public class Tokens {
    private final String[] tokens;

    public Tokens(String[] tokens) {
        this.tokens = tokens;
    }

    public boolean isEmpty() {
        return tokens.length == 0;
    }

    public String name(int i) {
        return tokens[i];
    }

    public int number(int i) {
        return Integer.parseInt(tokens[i]);
    }
}
