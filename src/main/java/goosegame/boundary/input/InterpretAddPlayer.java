package goosegame.boundary.input;

import goosegame.usecase.add_player.AddPlayer;

public class InterpretAddPlayer implements Interpreter {
    private final AddPlayer addPlayer;

    public InterpretAddPlayer(AddPlayer addPlayer) {
        this.addPlayer = addPlayer;
    }

    @Override
    public boolean interpret(CommandLine commandLine) {
        return commandLine.interpret("(add player) (\\w*)", tokens -> {
            String player = tokens.name(2);
            addPlayer.doAdd(player);
        });
    }
}
