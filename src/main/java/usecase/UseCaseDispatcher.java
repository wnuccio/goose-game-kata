package usecase;

public class UseCaseDispatcher {
    private AddPlayerUseCase addPlayerUseCase;
    private MovePlayerUseCase movePlayerUseCase;
    private ResetGameUseCase resetGameUseCase;

    public UseCaseDispatcher(AddPlayerUseCase addPlayerUseCase, MovePlayerUseCase movePlayerUseCase, ResetGameUseCase resetGameUseCase) {
        this.addPlayerUseCase = addPlayerUseCase;
        this.movePlayerUseCase = movePlayerUseCase;
        this.resetGameUseCase = resetGameUseCase;
    }

    public void acceptCommand(String commandLine) {
        if ("add".equals(operation(commandLine)))
            addPlayerUseCase.acceptCommand(commandLine);
        else if ("move".equals(operation((commandLine))))
            movePlayerUseCase.acceptCommand(commandLine);
        else if ("reset".equals(operation((commandLine))))
            resetGameUseCase.acceptCommand(commandLine);
        else
            throw new IllegalArgumentException("Error in command line: " + commandLine);
    }

    private String operation(String commandLine) {
        return commandLine.split(" ")[0];
    }
}
