package main;

import boundary.ConsolePresenter;
import boundary.MoveCommandParser;
import boundary.OutputBoundary;
import usecase.UseCaseDispatcher;
import usecase.add_player.AddPlayerUseCase;
import usecase.add_player.Players;
import usecase.move_player.DiceRoller;
import usecase.move_player.MovePlayer;
import usecase.move_player.MovePlayerUseCase;
import usecase.move_player.RollAndMove;
import usecase.reset_game.ResetGameService;

import java.util.Scanner;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        Scanner inputScanner = new Scanner(System.in);
        return () -> inputScanner.nextLine();
    }

    protected OutputBoundary getOutputBoundary() {
        return string -> System.out.println(string);
    }

    protected DiceRoller diceRoller() {
        throw new UnsupportedOperationException();
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        ConsolePresenter presenter = new ConsolePresenter(outputBoundary);
        Players players = new Players();

        UseCaseDispatcher useCaseDispatcher = useCaseDispatcher(presenter, players);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }

    private UseCaseDispatcher useCaseDispatcher(ConsolePresenter presenter, Players players) {
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, presenter);
        MovePlayerUseCase movePlayer = movePlayerUseCase(presenter, players);
        MoveCommandParser moveCommandParser = new MoveCommandParser();
        ResetGameService resetGameService = new ResetGameService(players);

        return new UseCaseDispatcher(resetGameService, addPlayerUseCase, movePlayer, moveCommandParser);
    }

    private MovePlayerUseCase movePlayerUseCase(ConsolePresenter presenter, Players players) {
        MovePlayer movePlayer = new MovePlayer(players, presenter);
        return new RollAndMove(diceRoller(), movePlayer);
    }
}
