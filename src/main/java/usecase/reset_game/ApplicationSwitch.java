package usecase.reset_game;

public class ApplicationSwitch {
    private boolean isOn = true;

    void turnOff() {
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }
}
