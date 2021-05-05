package configuration;

import console.ConsolePresenter;
import usecase.*;
import player.Players;

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
        UseCaseDispatcher useCaseDispatcher = new UseCaseDispatcher(addPlayerUseCase, movePlayerUseCase);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }
}
