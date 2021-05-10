package boundary;

import boundary.move_player.MoveCommandParser;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;

public class UseCaseDispatcher extends Interpreter{

    private MovePlayerUseCase movePlayerUseCase;

    public UseCaseDispatcher(MovePlayerUseCase movePlayerUseCase) {
        super(null);
        this.movePlayerUseCase = movePlayerUseCase;
    }

    public void acceptCommand(String commandLine) {
        String command = firstTokenOf(commandLine);

        if ("move".equals(command)) {
            MoveCommandParser moveCommandParser = new MoveCommandParser();
            MoveCommand moveCommand = moveCommandParser.parse(commandLine);
            movePlayerUseCase.acceptCommand(moveCommand);
            return;
        }
    }

    private String firstTokenOf(String commandLine) {
        return commandLine.split(" ")[0];
    }
}
