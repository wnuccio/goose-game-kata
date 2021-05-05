package configuration;

import add_player.AddPlayerUseCase;
import add_player.OutputBoundary;
import add_player.Players;

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
        AddPlayerUseCase addPlayerUseCase = new AddPlayerUseCase(new Players(), outputBoundary);

        return new GooseGameApp(inputBoundary, addPlayerUseCase);
    }
}
