package goosegame.boundary.input;

import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

import java.util.List;

import static java.util.Arrays.asList;

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

        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer),
                new InterpretMovePlayer(movePlayer),
                new InterpretRollAndMove(rollAndMove),
                new InterpretReset(resetService)::doReset,
                new InterpretReset(resetService)::doStop,
                this::unrecognizedCommand
        );

        for (Interpreter i : intepreters) {
            if (i.interpret(this.commandLine)) return;
        }
    }

    private boolean unrecognizedCommand(CommandLine commandLine) {
        return false;
    }
}
