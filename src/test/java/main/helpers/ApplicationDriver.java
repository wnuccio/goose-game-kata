package main.helpers;

public class ApplicationDriver {
    private ApplicationRunner applicationRunner;

    public ApplicationDriver(ApplicationRunner applicationRunner) {
        this.applicationRunner = applicationRunner;
    }

    public void runApplication() {
        applicationRunner.runApplication();
    }

    public String acceptInput(String inputString) {
        return applicationRunner.acceptInput(inputString);
    }

    public void stopApplication() {
        applicationRunner.acceptInput("exit");
    }

    public String addPlayer(String playerName) {
        return applicationRunner.acceptInput("add player " + playerName);
    }

    public void resetGame() { applicationRunner.acceptInput("reset game"); }
}
