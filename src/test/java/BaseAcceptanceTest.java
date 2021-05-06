import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseAcceptanceTest {
    protected static ApplicationRunner gooseGame;

    @BeforeAll
    static void runApplication() {
        gooseGame = new ApplicationRunner();
        gooseGame.runApplication();
    }

    @AfterAll
    static void stopApplication() {
        gooseGame.stopApplication();
    }

    @BeforeEach
    void setUp() {
        resetGame();
    }

    protected String addPlayer(String playerName) {
        return gooseGame.acceptInput("add player " + playerName);
    }

    protected void resetGame() { gooseGame.acceptInput("reset game"); }
}
