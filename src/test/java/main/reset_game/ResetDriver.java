package main.reset_game;

import main.ApplicationDriver;

import static org.assertj.core.api.Assertions.fail;

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
//        assertThat(applicationRunner.isApplicationRunning()).isEqualTo(true);
        fail("");
    }

    public void verifyGameStopped() {
//        assertThat(applicationRunner.isApplicationRunning()).isEqualTo(false);
        fail("");
    }
}
