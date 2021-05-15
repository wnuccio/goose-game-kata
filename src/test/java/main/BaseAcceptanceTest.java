package main;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    private ApplicationDriver driver;
    private DiceRollerStub diceRollerStub;
    protected ApplicationRunner applicationRunner;

    @BeforeEach
    final void init() {
        SharedMemory sharedMemory = new SharedMemory();
        diceRollerStub = new DiceRollerStub();
        GooseGameAppBuilderForTest appBuilder = new GooseGameAppBuilderForTest(sharedMemory, diceRollerStub);

        driver = new ApplicationDriver(sharedMemory);
        applicationRunner = new ApplicationRunner(appBuilder);
        applicationRunner.runApplication();
    }

    protected ApplicationDriver driver() { return driver; }

    public DiceRollerStub diceRollerStub() {
        return diceRollerStub;
    }
}
