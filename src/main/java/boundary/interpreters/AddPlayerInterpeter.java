package boundary.interpreters;

import usecase.add_player.AddPlayer;

public class AddPlayerInterpeter extends Interpreter {
    private AddPlayer addPlayer;

    public AddPlayerInterpeter(AddPlayer addPlayer, Interpreter nextInterpreter) {
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
