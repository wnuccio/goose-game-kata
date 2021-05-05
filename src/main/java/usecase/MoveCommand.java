package usecase;

import static java.lang.Integer.parseInt;

public class MoveCommand {
    private String commandLine;

    public MoveCommand(String commandLine) {
        this.commandLine = commandLine;
    }

    public String playerName() {
        return token(1);
    }

    public int firstDice() {
        return numberAt(2);
    }

    public int secondDice() {
        return numberAt(3);
    }

    private String token(int i) {
        String[] tokens = commandLine.split(" ");
        return tokens[i];
    }

    private int numberAt(int i) {
        String character = token(i).substring(0, 1);
        return parseInt( character);
    }
}
