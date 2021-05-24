package app.domain.interpreter;

import java.util.function.Consumer;
import java.util.regex.Matcher;

import static app.domain.interpreter.Tokens.empty;
import static java.util.regex.Pattern.compile;

public class CommandLine {
    private final String commandLine;

    public CommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    private Tokens tokenize(String regex) {
        Matcher matcher = compile(regex).matcher(commandLine);

        if (! matcher.find()) return empty();

        int tokenNumbers = matcher.groupCount() + 1;
        String[] tokens = new String[tokenNumbers];

        for (int i = 0; i< tokenNumbers; i++) {
            tokens[i] = matcher.group(i);
        }
        return new Tokens(tokens);
    }

    public boolean interpret(String regex, Consumer<Tokens> execution) {
        Tokens tokens = tokenize(regex);
        if (tokens.isEmpty()) return false;
        execution.accept(tokens);
        return true;
    }

}
