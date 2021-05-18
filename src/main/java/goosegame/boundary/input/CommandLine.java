package goosegame.boundary.input;

import java.util.function.Consumer;
import java.util.regex.Matcher;

import static java.util.regex.Pattern.compile;

public class CommandLine {
    private final String commandLine;

    public CommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    private static String[] toTokens(Matcher m) {
        if (! m.find()) return new String[0];

        String[] tokens = new String[m.groupCount() + 1];
        for (int i=0; i< m.groupCount() + 1; i++) {
            tokens[i] = m.group(i);
        }
        return tokens;
    }

    private String[] parse(String regex) {
        return toTokens(compile(regex).matcher(commandLine));
    }

    public boolean interpret(String regex, Consumer<String[]> execution) {
        String[] tokens = parse(regex);
        if (tokens.length == 0) return false;
        execution.accept(tokens);
        return true;
    }
}
