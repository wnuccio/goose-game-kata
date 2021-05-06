package usecase;

import java.util.Map;
import java.util.Optional;

public class UseCaseDispatcher {

    private Map<String, UseCase> useCaseMap;

    public UseCaseDispatcher(Map<String, UseCase> useCaseMap) {
        this.useCaseMap = useCaseMap;
    }

    private Optional<UseCase> selectUseCaseBy(String command) {
        return Optional.ofNullable(useCaseMap.get(command));
    }

    public void acceptCommand(String commandLine) {
        String command = firstTokenOf(commandLine);

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
