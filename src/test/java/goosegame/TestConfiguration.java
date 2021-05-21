package goosegame;

import goosegame.boundary.application.InputOutput;
import goosegame.config.GooseGameAppBuilder;
import goosegame.config.RandomDiceRoller;
import goosegame.domain.DiceRoller;

public class TestConfiguration {
    private SharedMemory sharedMemory;
    private final DiceRoller diceRoller;
    private GooseGameAppBuilder builder;
    private ApplicationDriver applicationDriver;
    private ApplicationRunner applicationRunner;

    public TestConfiguration() {
        this(new RandomDiceRoller());
    }

    public TestConfiguration(DiceRoller diceRoller) {
        this.diceRoller = diceRoller;
    }

    private GooseGameAppBuilder builder() {
        if (builder == null) builder = new GooseGameAppBuilder() {
            @Override
            protected InputOutput getInputOutput() {
                return sharedMemory();
            }

            @Override
            protected DiceRoller diceRoller() {
                return TestConfiguration.this.diceRoller;
            }
        };

        return this.builder;
    }

    private SharedMemory sharedMemory() {
        if (sharedMemory == null) sharedMemory = new SharedMemory();
        return sharedMemory;
    }

    public ApplicationDriver applicationDriver() {
        if (this.applicationDriver == null) applicationDriver = new ApplicationDriver(sharedMemory());
        return this.applicationDriver;
    }

    public ApplicationRunner applicationRunner() {
        if (applicationRunner == null) applicationRunner = new ApplicationRunner(builder());
        return applicationRunner;
    }
}
