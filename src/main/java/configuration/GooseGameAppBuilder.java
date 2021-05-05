package configuration;

import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;
import add_player.Players;
import dispatcher.UseCaseDispatcher;

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
        Players players = new Players();
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(players, outputBoundary);
        UseCaseDispatcher useCaseDispatcher = new UseCaseDispatcher(addPlayerUseCase);

        return new GooseGameApp(inputBoundary, useCaseDispatcher);
    }
}
