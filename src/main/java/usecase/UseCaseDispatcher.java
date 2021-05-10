package usecase;

import boundary.MoveCommandParser;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;
import usecase.reset_game.ResetGameService;

import java.util.Map;
import java.util.Optional;

public class UseCaseDispatcher {

    private Map<String, UseCase> useCaseMap;
    private ResetGameService resetGameService;
    private MovePlayerUseCase movePlayerUseCase;
    private MoveCommandParser moveCommandParser;

    public UseCaseDispatcher(Map<String, UseCase> useCaseMap, ResetGameService resetGameService, MovePlayerUseCase movePlayerUseCase, MoveCommandParser moveCommandParser) {
        this.useCaseMap = useCaseMap;
        this.resetGameService = resetGameService;
        this.movePlayerUseCase = movePlayerUseCase;
        this.moveCommandParser = moveCommandParser;
    }

    private Optional<UseCase> selectUseCaseBy(String command) {
        return Optional.ofNullable(useCaseMap.get(command));
    }

    public void acceptCommand(String commandLine) {
        String command = firstTokenOf(commandLine);

        if ("reset".equals(command)) {
            resetGameService.doReset();
            return;
        }

        if ("move".equals(command)) {
            MoveCommand moveCommand = moveCommandParser.parse(commandLine);
            movePlayerUseCase.acceptCommand(moveCommand);
            return;
        }

        UseCase useCase =
                selectUseCaseBy(command)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Error in command line: " + commandLine));

        useCase.acceptCommand(commandLine);

    }

    private String firstTokenOf(String commandLine) {
        return commandLine.split(" ")[0];
    }
}
