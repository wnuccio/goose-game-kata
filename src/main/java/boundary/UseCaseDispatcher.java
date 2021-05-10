package boundary;

import usecase.add_player.AddPlayerUseCase;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;
import usecase.reset_game.ResetGameService;

public class UseCaseDispatcher {

    private ResetGameService resetGameService;
    private AddPlayerUseCase addPlayer;
    private MovePlayerUseCase movePlayerUseCase;

    public UseCaseDispatcher(ResetGameService resetGameService, AddPlayerUseCase addPlayer, MovePlayerUseCase movePlayerUseCase) {
        this.resetGameService = resetGameService;
        this.addPlayer = addPlayer;
        this.movePlayerUseCase = movePlayerUseCase;
    }

    public void acceptCommand(String commandLine) {
        String command = firstTokenOf(commandLine);

        if ("add".equals(command)) {
            AddPlayerCommandParser parser = new AddPlayerCommandParser();
            String player = parser.playerName(commandLine);
            addPlayer.doAdd(player);
            return;
        }

        if ("reset".equals(command)) {
            resetGameService.doReset();
            return;
        }

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
