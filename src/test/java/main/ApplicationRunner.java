package main;

import domain.DiceRoller;

public class ApplicationRunner {
    private static ApplicationDriver driver = null;

    public ApplicationDriver runApplication() {
        DiceRollerStub diceRollerStub = new DiceRollerStub();
        return runApplication(diceRollerStub);
    }

    public ApplicationDriver runApplication(DiceRoller diceRoller) {
        if (driver != null) return driver;

        SharedMemory sharedMemory = new SharedMemory();
        DiceRollerStub diceRollerStub =
                diceRoller instanceof DiceRollerStub ? (DiceRollerStub) diceRoller : null;

        driver = new ApplicationDriver(sharedMemory, diceRollerStub);

        Main.injectedBuilder = new GooseGameAppBuilderForTest(sharedMemory, diceRoller);

        Thread thread = new Thread(this::invokeMainDetectingCrash);
        thread.setDaemon(true);
        thread.start();

        waitAbit();
        if (driver == null) throw new IllegalStateException("Wait more for app thread starting");
        return driver;
    }

    private void invokeMainDetectingCrash() {
        try {
            Main.main(new String[]{});
            driver = null;                    // normal termination: the application thread ends

        } catch (Exception e) {
            e.printStackTrace();
            driver = null;                    // crash: the application thread ends
        }
    }

    public static boolean isApplicationRunning() {
        waitAbit();
        return driver != null;
    }

    private static void waitAbit() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


//    no crash detection
//
//    private static void invokeMain() {
//        applicationRunning = true;
//        Main.main(Main.ARGS_FOR_TEST);
//        applicationRunning = false; // explicit stop
//    }


}
