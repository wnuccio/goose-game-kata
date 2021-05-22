package _1_actions.game;

public class GameSwitch {
    private boolean isOn = true;

    public void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}
