package main.helpers;

import static java.lang.String.format;

public class ApplicationDriver {
    private ApplicationRunner applicationRunner;

    public ApplicationDriver(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    public void runApplication() {
        applicationRunner.runApplication();
    }

    public void stopApplication() {
        applicationRunner.acceptInput("exit");
    }

    public String addPlayer(String playerName) {
        return applicationRunner.acceptInput("add player " + playerName);
    }

    public String movePlayer(String player, int firstDice, int secondDice) {
        return applicationRunner.acceptInput(format("move %s %d, %d", player, firstDice, secondDice));
    }

    public void resetGame() { applicationRunner.acceptInput("reset game"); }
}
