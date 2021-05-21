package app.config;

import app.domain.command.Interpreter;
import app.domain.game.*;
import app.domain.movement.*;
import app.domain.player.AddPlayer;
import app.domain.player.InterpretAddPlayer;
import app.domain.player.PlayerPresenter;
import app.domain.player.Players;

import java.util.List;

import static app.domain.game.CommandLineProcessor.ignoreUnrecognizedCommands;
import static java.util.Arrays.asList;

public class AppConfiguration {

    private final GooseGameAppBuilder builder;
    private Game game;
    private GameSwitch gameSwitch;
    private Players players;
    private InputOutput inputOutput;
    private DiceRoller diceRoller;

    public AppConfiguration(GooseGameAppBuilder builder) {
        this.builder = builder;
    }

    public Game buildApplication() {
        return gooseGameApp();
    }

    private Game gooseGameApp() {
        if (game == null) game = new Game(applicationSwitch(), inputOutput(), interpreter());
        return game;
    }

    private GameSwitch applicationSwitch() {
        if (gameSwitch == null) gameSwitch = new GameSwitch();
        return gameSwitch;
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

    private CommandLineProcessor interpreter() {
        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer()),
                new InterpretMovePlayer(movePlayer()),
                new InterpretRollAndMove(rollAndMove()),
                new InterpretReset(resetService())::doReset,
                new InterpretReset(resetService())::doStop,
                ignoreUnrecognizedCommands
        );

        return new CommandLineProcessor(intepreters);
    }

    private AddPlayer addPlayer() {
        return new AddPlayer(players(), presenter());
    }

    private ResetGame resetService() {
        return new ResetGame(applicationSwitch(), players());
    }

    private MovePlayer movePlayer() {
        return new MovePlayer(players(), computeMovement(), movementPresenter());
    }

    private RuleProcessor computeMovement() {
        return new RuleProcessor(players());
    }

    private RollAndMove rollAndMove() {
        return new RollAndMove(diceRoller(), movePlayer());
    }
}
