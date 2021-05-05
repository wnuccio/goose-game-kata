package dispatcher;

import add_player.AddPlayerUseCase;

public class UseCaseDispatcher {
    private AddPlayerUseCase addPlayerUseCase;

    public UseCaseDispatcher(AddPlayerUseCase addPlayerUseCase) {
        this.addPlayerUseCase = addPlayerUseCase;
    }

    public void acceptCommand(String commandLine) {
        addPlayerUseCase.acceptCommand(commandLine);
    }
}
