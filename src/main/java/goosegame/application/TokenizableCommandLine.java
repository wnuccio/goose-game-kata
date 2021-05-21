package goosegame.application;

import experiments.Tokens;
import goosegame.domain.CommandLine;

import java.util.function.Consumer;
import java.util.regex.Matcher;

import static experiments.Tokens.empty;
import static java.util.regex.Pattern.compile;

public class TokenizableCommandLine implements CommandLine {
    private final String commandLine;

    public TokenizableCommandLine(String commandLine) {
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

    @Override
    public boolean interpret(String regex, Consumer<Tokens> execution) {
        Tokens tokens = tokenize(regex);
        if (tokens.isEmpty()) return false;
        execution.accept(tokens);
        return true;
    }
}
