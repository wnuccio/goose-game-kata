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
        if (gooseGameApp == null) gooseGameApp = new GooseGameApp(applicationSwitch(), inputOutput(), interpretersChain());
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

    private OutputPresenter presenter() {
        return new OutputPresenter(inputOutput());
    }

    ///// INTERPRETERS CHAIN ///////////////////////////////////////////////////////////////
    private Interpreter interpretersChain() {
        return resetInterpeter();
    }

    private ResetInterpeter resetInterpeter() {
        return new ResetInterpeter(resetService(), addPlayerInterpeter());
    }

    private AddPlayerInterpeter addPlayerInterpeter() {
        return new AddPlayerInterpeter(addPlayer(), movePlayerInterpreter());
    }

    private MovePlayerInterpreter movePlayerInterpreter() {
        return new MovePlayerInterpreter(rollAndMove(), movePlayer(), null);
    }
    ///// INTERPRETERS CHAIN ///////////////////////////////////////////////////////////////

    private AddPlayerUseCase addPlayer() {
        return new AddPlayerUseCase(players(), presenter());
    }

    private ResetService resetService() {
        return new ResetService(applicationSwitch(), players());
    }

    private MovePlayer movePlayer() {
        return new MovePlayer(players(), computeMovement(), presenter());
    }

    private ComputeMovement computeMovement() {
        return new ComputeMovement(players());
    }

    private RollAndMove rollAndMove() {
        return new RollAndMove(diceRoller(), movePlayer());
    }
}
