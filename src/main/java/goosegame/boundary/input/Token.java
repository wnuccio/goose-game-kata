package goosegame.boundary.input;

import java.util.Objects;

public class Token {
    private final TokenType type;
    private final String content;

    public Token(TokenType type, String content) {
        this.type = type;
        this.content = content;
    }

    public static Token token(TokenType type) {
        return new Token(type, "");
    }

    public static Token token(TokenType type, String content) {
        return new Token(type, content);
    }

    public TokenType type() {
        return type;
    }

    public String content() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return type == token.type && Objects.equals(content, token.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content);
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
