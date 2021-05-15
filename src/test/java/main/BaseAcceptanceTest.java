package main;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    private ApplicationDriver driver;
    private DiceRollerStub diceRollerStub;

    @BeforeEach
    final void init() {
        SharedMemory sharedMemory = new SharedMemory();
        diceRollerStub = new DiceRollerStub();
        GooseGameAppBuilderForTest appBuilder = new GooseGameAppBuilderForTest(sharedMemory, diceRollerStub);
        new ApplicationRunner(appBuilder).runApplication();

        driver = new ApplicationDriver(sharedMemory);
    }

    protected ApplicationDriver driver() { return driver; }

    public DiceRollerStub diceRollerStub() {
        return diceRollerStub;
    }
}
