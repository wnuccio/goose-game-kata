package experiments;

public class Tokens {
    private final String[] tokens;

    public Tokens(String[] tokens) {
        this.tokens = tokens;
    }

    public static Tokens empty() {
        return new Tokens(new String[0]);
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
