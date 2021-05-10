package main.reset_game;

import main.test.ApplicationDriver;

public class ResetGameDriver {
    private ApplicationDriver applicationDriver;

    public ResetGameDriver(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    public void resetGame() { applicationDriver.acceptInput("reset game"); }
}
