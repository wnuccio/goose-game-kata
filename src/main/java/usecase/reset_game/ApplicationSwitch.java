package usecase.reset_game;

public class ApplicationSwitch {
    private boolean isOff = false;

    void turnOff() {
        isOff = true;
    }

    public boolean isOff() {
        return isOff;
    }
}
