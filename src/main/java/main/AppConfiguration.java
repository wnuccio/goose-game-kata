package main;

import boundary.application.GooseGameApp;
import boundary.application.InputOutput;
import boundary.interpreters.AddPlayerInterpeter;
import boundary.interpreters.Interpreter;
import boundary.interpreters.MovePlayerInterpreter;
import boundary.interpreters.ResetInterpeter;
import boundary.output.OutputPresenter;
import domain.DiceRoller;
import domain.Players;
import usecase.add_player.AddPlayerUseCase;
import usecase.move_player.ComputeMovement;
import usecase.move_player.MovePlayer;
import usecase.move_player.RollAndMove;
import usecase.reset_game.ApplicationSwitch;
import usecase.reset_game.ResetService;

public class AppConfiguration {

    private GooseGameAppBuilder builder;
    private DiceRoller diceRoller;

    public AppConfiguration(GooseGameAppBuilder builder) {
        this.builder = builder;
    }

    public GooseGameApp buildApplication() {
        InputOutput inputOutput = builder.getInputOutput();
        OutputPresenter presenter = new OutputPresenter(inputOutput);
        Players players = new Players();
        ApplicationSwitch applicationSwitch = new ApplicationSwitch();
        Interpreter interpreter = interpretersChain(presenter, players, applicationSwitch);

        return new GooseGameApp(applicationSwitch, inputOutput, interpreter);
    }

    private Interpreter interpretersChain(OutputPresenter presenter, Players players, ApplicationSwitch applicationSwitch) {
        return new ResetInterpeter(resetService(players, applicationSwitch),
                new AddPlayerInterpeter(addPlayer(presenter, players),
                        new MovePlayerInterpreter(
                                rollAndMove(presenter, players),
                                movePlayer(presenter, players),
                                null)));
    }

    private AddPlayerUseCase addPlayer(OutputPresenter presenter, Players players) {
        return new AddPlayerUseCase(players, presenter);
    }

    private ResetService resetService(Players players, ApplicationSwitch applicationSwitch) {
        return new ResetService(applicationSwitch, players);
    }

    private MovePlayer movePlayer(OutputPresenter presenter, Players players) {
        return new MovePlayer(players, new ComputeMovement(players), presenter);
    }

    private RollAndMove rollAndMove(OutputPresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, new ComputeMovement(players), presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }

    private DiceRoller diceRoller() {
        if (diceRoller == null) diceRoller = builder.diceRoller();
        return diceRoller;
    }
}
