package boundary.move_player;

import boundary.CommandLineParser;
import domain.Dice;
import usecase.move_player.MoveCommand;

public class MoveCommandParser {

    public MoveCommand parse(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);
        String player = parser.token(1);
        boolean hasDiceValues = parser.tokenNumber() != 2;

        MoveCommand result = new MoveCommand(player);
        if (hasDiceValues) {
            int firstDice = parser.digitAtToken(2);
            int secondDice = parser.digitAtToken(3);
            result = new MoveCommand(player, new Dice(firstDice, secondDice));
        }
        return result;
    }

}
