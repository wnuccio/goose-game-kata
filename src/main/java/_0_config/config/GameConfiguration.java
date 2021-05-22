package _0_config.config;

import _1_actions.controller.CommandLineProcessor;
import _1_actions.controller.Game;
import _1_actions.game.InterpretResetGame;
import _1_actions.game.InterpretStopGame;
import _1_actions.game.StopOrResetGame;
import _1_actions.player.add.AddPlayer;
import _1_actions.player.add.InterpretAddPlayer;
import _1_actions.player.add.PlayerPresenter;
import _1_actions.player.move.InterpretMovePlayer;
import _1_actions.player.move.MovePlayer;
import _1_actions.player.move.rules.RuleProcessor;
import _1_actions.player.rollmove.DiceRoller;
import _1_actions.player.rollmove.InterpretRollAndMove;
import _1_actions.player.rollmove.RollAndMove;
import _2_domain.game.GameSwitch;
import _2_domain.interpreter.Interpreter;
import _2_domain.player.Players;
import _2_domain.presenter.StringBuilderPresenter;

import java.util.List;

import static _1_actions.controller.CommandLineProcessor.ignoreUnrecognizedCommands;
import static java.util.Arrays.asList;

public class GameConfiguration {

    private final GooseGameAppBuilder builder;
    private Game game;
    private GameSwitch gameSwitch;
    private Players players;
    private InputOutput inputOutput;
    private DiceRoller diceRoller;

    public GameConfiguration(GooseGameAppBuilder builder) {
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

    private StringBuilderPresenter movementPresenter() {
        return new StringBuilderPresenter(inputOutput());
    }

    private CommandLineProcessor interpreter() {
        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer()),
                new InterpretMovePlayer(movePlayer()),
                new InterpretRollAndMove(rollAndMove()),
                new InterpretResetGame(resetService()),
                new InterpretStopGame(resetService()),
                ignoreUnrecognizedCommands
        );

        return new CommandLineProcessor(intepreters);
    }

    private AddPlayer addPlayer() {
        return new AddPlayer(players(), presenter());
    }

    private StopOrResetGame resetService() {
        return new StopOrResetGame(applicationSwitch(), players());
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
