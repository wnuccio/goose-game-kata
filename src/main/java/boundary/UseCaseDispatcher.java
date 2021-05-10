package boundary;

import boundary.add_player.AddPlayerCommandParser;
import boundary.move_player.MoveCommandParser;
import usecase.add_player.AddPlayerUseCase;
import usecase.move_player.MoveCommand;
import usecase.move_player.MovePlayerUseCase;
import usecase.reset_game.ResetService;

public class UseCaseDispatcher extends Interpreter{

    private ResetService resetService;
    private AddPlayerUseCase addPlayer;
    private MovePlayerUseCase movePlayerUseCase;

    public UseCaseDispatcher(ResetService resetService, AddPlayerUseCase addPlayer, MovePlayerUseCase movePlayerUseCase) {
        super(null);
        this.resetService = resetService;
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
