package main.reset_game;

import main.ApplicationDriver;
import main.ApplicationRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetDriver {
    private ApplicationDriver applicationDriver;

    public ResetDriver(ApplicationDriver applicationDriver) {
        this.applicationDriver = applicationDriver;
    }

    public void resetGame() { applicationDriver.acceptInput("reset game"); }

    public void stopGame() {
        applicationDriver.acceptInput("stop game");
    }

    public void verifyGameRunning() {
        assertThat(ApplicationRunner.isApplicationRunning()).isEqualTo(true);
    }

    public void verifyGameStopped() {
        assertThat(ApplicationRunner.isApplicationRunning()).isEqualTo(false);
    }
}
