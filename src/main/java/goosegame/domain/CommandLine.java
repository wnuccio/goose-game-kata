package goosegame.domain;

import goosegame.lexer.Tokens;

import java.util.function.Consumer;

public interface CommandLine {
    boolean interpret(String regex, Consumer<Tokens> execution);
}
