package app.domain.command;

import java.util.function.Consumer;

public interface CommandLine {
    boolean interpret(String regex, Consumer<Tokens> execution);
}
