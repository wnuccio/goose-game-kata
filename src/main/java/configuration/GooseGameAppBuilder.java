package configuration;

import console.ConsolePresenter;
import player.Players;
import usecase.*;

import java.util.Scanner;

public class GooseGameAppBuilder {

    protected InputBoundary getInputBoundary() {
        Scanner inputScanner = new Scanner(System.in);
        return () -> inputScanner.nextLine();
    }

    protected OutputBoundary getOutputBoundary() {
        return string -> System.out.println(string);
    }

    public final GooseGameApp buildApplication() {
        InputBoundary inputBoundary = getInputBoundary();
        OutputBoundary outputBoundary = getOutputBoundary();
        ConsolePresenter presenter = new ConsolePresenter(outputBoundary);
        Players players = new Players();
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, outputBoundary);
        MovePlayerUseCase movePlayerUseCase = new MovePlayerUseCase(players, presenter);
        ResetGameUseCase resetGameUseCase = new ResetGameUseCase(players);
        UseCaseDispatcher useCaseDispatcher = new UseCaseDispatcher(addPlayerUseCase, movePlayerUseCase, resetGameUseCase);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }
}
