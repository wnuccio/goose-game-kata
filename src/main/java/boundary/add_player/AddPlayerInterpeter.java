package boundary.add_player;

import boundary.CommandLineParser;
import boundary.Interpreter;
import usecase.add_player.AddPlayerUseCase;

public class AddPlayerInterpeter extends Interpreter {
    private AddPlayerUseCase addPlayer;

    public AddPlayerInterpeter(AddPlayerUseCase addPlayer, Interpreter nextInterpreter) {
        super(nextInterpreter);
        this.addPlayer = addPlayer;
    }

    public void acceptCommand(String commandLine) {
        CommandLineParser parser = new CommandLineParser(commandLine);

        if (parser.lineStartsWith("add player") && parser.tokenNumber() == 3) {
            String player = parser.token(2);
            addPlayer.doAdd(player);
            return;
        }

        super.acceptCommand(commandLine);
    }
}
