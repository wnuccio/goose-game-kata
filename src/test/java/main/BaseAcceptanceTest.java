package main;

import main.reset_game.ResetDriver;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    private ApplicationDriver driver;
    private DiceRollerStub diceRollerStub;
    protected ApplicationRunner applicationRunner;

    @BeforeEach
    final void init() {
        SharedMemory sharedMemory = new SharedMemory();
        diceRollerStub = new DiceRollerStub();
        driver = new ApplicationDriver(sharedMemory);
        GooseGameAppBuilderForTest appBuilder = new GooseGameAppBuilderForTest(sharedMemory, diceRollerStub);
        applicationRunner = new ApplicationRunner(appBuilder);
        applicationRunner.runApplication();
        new ResetDriver(driver).resetGame();
    }

    protected ApplicationDriver driver() { return driver; }

    public DiceRollerStub diceRollerStub() {
        return diceRollerStub;
    }
}
