package goosegame.boundary.input;

import goosegame.domain.Dice;
import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MoveCommand;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

public class CommandsInterpreter {
    private final AddPlayer addPlayer;

    private final MovePlayer movePlayer;
    private final RollAndMove rollAndMove;
    private final ResetService resetService;
    private CommandLine commandLine;

    public CommandsInterpreter(AddPlayer addPlayer, MovePlayer movePlayer, RollAndMove rollAndMove, ResetService resetService) {
        this.addPlayer = addPlayer;
        this.movePlayer = movePlayer;
        this.rollAndMove = rollAndMove;
        this.resetService = resetService;
    }

    public void acceptCommand(String commandLine) {
        this.commandLine = new CommandLine(commandLine);

        if (interpretAddPlayer()) return;
        if (interpretMovePlayer()) return;
        if (interpretRollAndMove()) return;
        if (interpretReset()) return;
        if (interpretStop()) return;

        doNothing();
    }

    private boolean interpretAddPlayer() {
        return commandLine.interpret("(add player) (\\w*)", tokens -> {
            String player = tokens.name(2);
            addPlayer.doAdd(player);
        });
    }

    private boolean interpretMovePlayer() {
        return commandLine.interpret("(move) (\\w*) ([1-6]), ([1-6])", tokens -> {
            String player = tokens.name(2);
            int dice1 = tokens.number(3);
            int dice2 = tokens.number(4);
            MoveCommand command = new MoveCommand(player, new Dice(dice1, dice2));
            movePlayer.acceptCommand(command);
        });
    }

    private boolean interpretRollAndMove() {
        return commandLine.interpret("(move) (\\w*)", tokens -> {
            String player = tokens.name(2);
            rollAndMove.acceptCommand(player);
        });
    }

    private boolean interpretReset() {
        return commandLine.interpret("reset game", resetService::doReset);
    }

    private boolean interpretStop() {
        return commandLine.interpret("stop game", resetService::doStop);
    }

    private void doNothing() {
    }
}
