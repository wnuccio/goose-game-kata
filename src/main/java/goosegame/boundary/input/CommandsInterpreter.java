package goosegame.boundary.input;

import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.move_player.MovePlayer;
import goosegame.usecase.move_player.RollAndMove;
import goosegame.usecase.reset_game.ResetService;

import java.util.List;
import java.util.function.Function;

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
            this::unrecognizedCommand
        );

        for(Interpreter i: intepreters) {
            if (i.interpret(this.commandLine)) return;
        }

        interpretCommands(
            this::interpretReset,
            this::interpretStop,
            this::unrecognizedCommand
        );
    }

    void interpretCommands(Function<CommandLine, Boolean>... interpretationsFunctions) {
        for (Function<CommandLine, Boolean> f: interpretationsFunctions) {
            Boolean applied = f.apply(commandLine);
            if (applied) break;
        }
    }

    private boolean interpretReset(CommandLine commandLine) {
        return commandLine.interpret("reset game", resetService::doReset);
    }

    private boolean interpretStop(CommandLine commandLine) {
        return commandLine.interpret("stop game", resetService::doStop);
    }

    private boolean unrecognizedCommand(CommandLine commandLine) {
        return false;
    }
}
