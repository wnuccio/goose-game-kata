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

    public void moveOnPosition60(String player) {
        for (int i = 0; i < 6; i++) movePlayer(player, 5, 5);
    }

    public void moveOnPosition4(String player) {
        movePlayer(player, 2, 2);
    }

    public void moveOnPosition3(String player) {
        movePlayer(player, 2, 1);
    }
}
