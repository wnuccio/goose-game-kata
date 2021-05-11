package main.move_player;

import main.test.ApplicationDriver;

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
        // avoid goose positions: (5, 9, 14, 18, 23, 27);
        movePlayer(player, 2, 2);
        movePlayer(player, 2, 2);
        movePlayer(player, 2, 3);
        movePlayer(player, 2, 2);
        movePlayer(player, 2, 3);
        movePlayer(player, 2, 2);
        movePlayer(player, 2, 2); // on position 30
        for (int i = 0; i < 3; i++) movePlayer(player, 5, 5);

    }

    public void moveOnPosition4(String player) {
        movePlayer(player, 2, 2);
    }

    public void moveOnPosition3(String player) {
        movePlayer(player, 2, 1);
    }

    public void moveOnPosition10(String player) {
        movePlayer(player, 2, 2);
        movePlayer(player, 2, 2);
        movePlayer(player, 1, 1);
    }

    public void moveOnPosition15(String player) {
        moveOnPosition10(player);
        movePlayer(player, 2, 3);
    }

    public void moveOnPosition17(String player) {
        moveOnPosition15(player);
        movePlayer(player, 1, 1);
    }
}
