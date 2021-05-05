import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseAcceptanceTest {
    protected static ApplicationRunner gooseGame;

    @BeforeAll
    static void setUp() {
        gooseGame = new ApplicationRunner();
        gooseGame.runApplication();
    }

    @AfterAll
    static void tearDown() {
        gooseGame.stopApplication();
    }

    protected String addPlayer(String playerName) {
        return gooseGame.acceptInput("add player " + playerName);
    }
}
