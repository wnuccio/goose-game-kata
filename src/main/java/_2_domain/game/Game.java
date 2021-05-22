package _2_domain.game;

public class Game {
    private boolean isOn = true;

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}
