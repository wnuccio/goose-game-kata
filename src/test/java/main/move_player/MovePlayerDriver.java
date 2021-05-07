package main.move_player;

import main.ApplicationDriver;

import static java.lang.String.format;

public class MovePlayerDriver {
    private ApplicationDriver driver;

    public MovePlayerDriver(ApplicationDriver driver) {
        this.driver = driver;
    }

    public String movePlayer(String player, int firstDice, int secondDice) {
        return driver.acceptInput(format("move %s %d, %d", player, firstDice, secondDice));
    }

    public String movePlayer(String player) {
        return driver.acceptInput(format("move %s", player));
    }

    public void moveOnPosition60(String pippo) {
        for (int i = 0; i < 6; i++) movePlayer(pippo, 5, 5);
    }
}
