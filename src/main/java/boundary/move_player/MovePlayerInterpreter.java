package boundary.move_player;

import boundary.CommandLineParser;
import boundary.Interpreter;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;

public class MovePlayerInterpreter extends Interpreter {

    private MovePlayerUseCase movePlayerUseCase;

    public MovePlayerInterpreter(MovePlayerUseCase movePlayer, Interpreter nextInterpreter) {
        super(nextInterpreter);
        this.movePlayerUseCase = movePlayer;
    }

    public void acceptCommand(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);

        if (parser.lineStartsWith("move")) {
            MoveCommandParser moveCommandParser = new MoveCommandParser();
            MoveCommand moveCommand = moveCommandParser.parse(commandLine);
            movePlayerUseCase.acceptCommand(moveCommand);
            return;
        }

        super.acceptCommand(commandLine);
    }
}
