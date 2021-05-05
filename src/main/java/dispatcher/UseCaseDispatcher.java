package dispatcher;

import add_player.AddPlayerUseCase;
import move_player.MovePlayerUseCase;

public class UseCaseDispatcher {
    private AddPlayerUseCase addPlayerUseCase;
    private MovePlayerUseCase movePlayerUseCase;

    public UseCaseDispatcher(AddPlayerUseCase addPlayerUseCase, MovePlayerUseCase movePlayerUseCase) {
        this.addPlayerUseCase = addPlayerUseCase;
        this.movePlayerUseCase = movePlayerUseCase;
    }

    public void acceptCommand(String commandLine) {
        if ("add".equals(operation(commandLine)))
            addPlayerUseCase.acceptCommand(commandLine);
        else
            movePlayerUseCase.acceptCommand(commandLine);
    }

    private String operation(String commandLine) {
        return commandLine.split(" ")[0];
    }
}
