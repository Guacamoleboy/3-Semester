package dk.project.service;

import dk.project.config.GameConfig;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class MiningService implements Callable<MiningResult> {

    // Attributes
    private final int minerNumber;
    private final GameConfig gameConfig = new GameConfig();

    // _______________________________________________________

    public MiningService(int minerNumber) {
        this.minerNumber = minerNumber;
    }

    // _______________________________________________________

    @Override
    public MiningResult call() throws Exception {

        // Initial Setup
        int totalGold = 0;
        int strikes = 0;
        long startTime = System.currentTimeMillis();

        // While loop over capacity
        while (totalGold < gameConfig.getCapacity()) {

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Miner " + minerNumber + " was interrupted!");
                break;
            }

            // Mining Time
            int sleepTime = ThreadLocalRandom.current().nextInt(200, 701);
            Thread.sleep(sleepTime);

            // Random Gold
            int goldFound = ThreadLocalRandom.current().nextInt(1, 21);
            totalGold += goldFound;

            // Strike Addition
            strikes++;

            // Display
            System.out.println("Miner " + minerNumber + " struck and found " + goldFound + " gold (total=" + totalGold + ")");

        }

        long duration = System.currentTimeMillis() - startTime;
        return new MiningResult(minerNumber, totalGold, strikes, duration);

    }

}