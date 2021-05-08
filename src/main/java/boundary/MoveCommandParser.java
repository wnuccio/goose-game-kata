package boundary;

import usecase.CommandLineParser;
import usecase.move_player.Dice;
import usecase.move_player.MoveCommand;

public class MoveCommandParser {
    private Dice dice;

    public MoveCommandParser(Dice dice) {
        this.dice = dice;
    }

    public MoveCommand parse(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);
        String player = parser.token(1);
        boolean hasDiceValues = parser.tokenNumber() != 2;

        MoveCommand result = new MoveCommand(player);
        if (hasDiceValues) {
            int firstDice = parser.digitAtToken(2);
            int secondDice = parser.digitAtToken(3);
            result = new MoveCommand(player, dice.from(firstDice, secondDice));
        }
        return result;
    }

}
