package boundary.move_player;

import boundary.CommandLineParser;
import boundary.Interpreter;
import domain.Dice;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;

public class MovePlayerInterpreter extends Interpreter {

    private MovePlayerUseCase movePlayerUseCase;
    private CommandLineParser parser;

    public MovePlayerInterpreter(MovePlayerUseCase movePlayer, Interpreter nextInterpreter) {
        super(nextInterpreter);
        this.movePlayerUseCase = movePlayer;
    }

    public void acceptCommand(String commandLine) {
        parser = new CommandLineParser(commandLine);

        if (parser.lineStartsWith("move")) {
            MoveCommand moveCommand = buildMoveCommand();
            movePlayerUseCase.acceptCommand(moveCommand);
            return;
        }

        super.acceptCommand(commandLine);
    }

    private MoveCommand buildMoveCommand() {
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
