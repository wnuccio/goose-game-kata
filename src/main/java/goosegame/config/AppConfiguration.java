package goosegame.config;

import goosegame.application.CommandLineIntepreter;
import goosegame.application.GooseGameApp;
import goosegame.application.InputOutput;
import goosegame.domain.DiceRoller;
import goosegame.domain.Interpreter;
import goosegame.domain.Players;
import goosegame.usecase.add_player.AddPlayer;
import goosegame.usecase.add_player.InterpretAddPlayer;
import goosegame.usecase.add_player.PlayerPresenter;
import goosegame.usecase.move_player.*;
import goosegame.usecase.reset_game.ApplicationSwitch;
import goosegame.usecase.reset_game.InterpretReset;
import goosegame.usecase.reset_game.ResetService;

import java.util.List;

import static goosegame.application.CommandLineIntepreter.ignoreUnrecognizedCommands;
import static java.util.Arrays.asList;

public class AppConfiguration {

    private final GooseGameAppBuilder builder;
    private GooseGameApp gooseGameApp;
    private ApplicationSwitch applicationSwitch;
    private Players players;
    private InputOutput inputOutput;
    private DiceRoller diceRoller;

    public AppConfiguration(GooseGameAppBuilder builder) {
        this.builder = builder;
    }

    public GooseGameApp buildApplication() {
        return gooseGameApp();
    }

    private GooseGameApp gooseGameApp() {
        if (gooseGameApp == null) gooseGameApp = new GooseGameApp(applicationSwitch(), inputOutput(), interpreter());
        return gooseGameApp;
    }

    private ApplicationSwitch applicationSwitch() {
        if (applicationSwitch == null) applicationSwitch = new ApplicationSwitch();
        return applicationSwitch;
    }

    private Players players() {
        if (players == null) players = new Players();
        return players;
    }

    private InputOutput inputOutput() {
        if (inputOutput == null) inputOutput = builder.getInputOutput();
        return inputOutput;
    }

    private DiceRoller diceRoller() {
        if (diceRoller == null) diceRoller = builder.diceRoller();
        return diceRoller;
    }

    private PlayerPresenter presenter() {
        return new PlayerPresenter(inputOutput());
    }

    private MovementPresenter movementPresenter() {
        return new MovementPresenter(inputOutput());
    }

    private CommandLineIntepreter interpreter() {
        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer()),
                new InterpretMovePlayer(movePlayer()),
                new InterpretRollAndMove(rollAndMove()),
                new InterpretReset(resetService())::doReset,
                new InterpretReset(resetService())::doStop,
                ignoreUnrecognizedCommands
        );

        return new CommandLineIntepreter(intepreters);
    }

    private AddPlayer addPlayer() {
        return new AddPlayer(players(), presenter());
    }

    private ResetService resetService() {
        return new ResetService(applicationSwitch(), players());
    }

    private MovePlayer movePlayer() {
        return new MovePlayer(players(), computeMovement(), movementPresenter());
    }

    private ComputeMovement computeMovement() {
        return new ComputeMovement(players());
    }

    private RollAndMove rollAndMove() {
        return new RollAndMove(diceRoller(), movePlayer());
    }
}
