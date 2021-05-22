package _0_config.config;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {

    private ApplicationDriver driver;
    private DiceRollerStub diceRollerStub;

    @BeforeEach
    final void init() {
        diceRollerStub = new DiceRollerStub();
        TestConfiguration configuration = new TestConfiguration(diceRollerStub);
        configuration.applicationRunner().runApplication();

        driver = configuration.applicationDriver();
    }

    protected ApplicationDriver driver() { return driver; }

    public DiceRollerStub diceRollerStub() {
        return diceRollerStub;
    }
}
