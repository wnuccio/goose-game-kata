package app.config;

import app.console.CommandLineProcessor;
import app.console.GameController;
import app.domain.game.Game;
import app.domain.game.InterpretResetGame;
import app.domain.game.InterpretStopGame;
import app.domain.game.StopOrResetGame;
import app.domain.interpreter.Interpreter;
import app.domain.movement.FindPlayer;
import app.domain.movement.InterpretMovePlayer;
import app.domain.movement.MovePlayer;
import app.domain.movement.PlayerOnTurnFactory;
import app.domain.player.*;
import app.domain.rollmove.DiceRoller;
import app.domain.rollmove.InterpretRollAndMove;
import app.domain.rollmove.RollAndMove;
import app.domain.rules.RuleProcessor;
import app.domain.rules.bouncing.BouncingRule;
import app.domain.rules.bridge.JumpOnBridgeRule;
import app.domain.rules.goose.GooseRule;
import app.domain.rules.switchrule.SwitchPlayersRule;

import java.util.List;

import static app.console.CommandLineProcessor.ignoreUnrecognizedCommands;
import static java.util.Arrays.asList;

public class GameConfiguration {

    private final GooseGameAppBuilder builder;
    private GameController gameController;
    private Game game;
    private Board board;
    private Players players;
    private InputOutput inputOutput;
    private DiceRoller diceRoller;

    public GameConfiguration(GooseGameAppBuilder builder) {
        this.builder = builder;
    }

    public GameController buildApplication() {
        return gooseGameApp();
    }

    private GameController gooseGameApp() {
        if (gameController == null) gameController = new GameController(applicationSwitch(), inputOutput(), interpreter());
        return gameController;
    }

    private Game applicationSwitch() {
        if (game == null) game = new Game();
        return game;
    }

    private Board board() {
        if (board == null) board = new Board();
        return board;
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

    private PlayerOnTurnFactory playerOnTurnFactory() {
        return new PlayerOnTurnFactory(inputOutput());
    }

    private CommandLineProcessor interpreter() {
        List<Interpreter> intepreters = asList(
                new InterpretAddPlayer(addPlayer()),
                new InterpretMovePlayer(findPlayer(movePlayer())),
                new InterpretRollAndMove(rollAndMove(findPlayer(movePlayer()))),
                new InterpretResetGame(resetService()),
                new InterpretStopGame(resetService()),
                ignoreUnrecognizedCommands
        );

        return new CommandLineProcessor(intepreters);
    }

    private FindPlayer findPlayer(MovePlayer movePlayer) {
        return new FindPlayer(players(), movePlayer);
    }

    private AddPlayer addPlayer() {
        return new AddPlayer(board(), players(), presenter());
    }

    private StopOrResetGame resetService() {
        return new StopOrResetGame(applicationSwitch(), players());
    }

    private MovePlayer movePlayer() {
        return new MovePlayer(computeMovement(), playerOnTurnFactory());
    }

    private RuleProcessor computeMovement() {
        BouncingRule bouncingRule = new BouncingRule(board());
        JumpOnBridgeRule jumpOnBridgeRule = new JumpOnBridgeRule(board());
        GooseRule gooseRule = new GooseRule();
        SwitchPlayersRule switchPlayersRule = new SwitchPlayersRule(players);

        return new RuleProcessor(board(), players(), bouncingRule, jumpOnBridgeRule, gooseRule, switchPlayersRule);
    }

    private RollAndMove rollAndMove(FindPlayer findPlayer) {
        return new RollAndMove(diceRoller(), findPlayer);
    }
}
