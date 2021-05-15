package main.reset_game;

import main.ApplicationDriver;

public class ResetDriver {
    private ApplicationDriver applicationDriver;

    public ResetDriver(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    public void resetGame() { applicationDriver.acceptInput("reset game"); }

}
