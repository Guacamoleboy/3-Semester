package dk.project;

import dk.project.config.GameConfig;
import dk.project.service.MiningResult;
import dk.project.service.MiningService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    // Attributes
    private static final int maxThreads = 10;

    // _____________________________________________________________

    public static void main(String[] args) throws Exception {

        // Initial Setup
        int totalMiners = GameConfig.getTotalMiners();
        ExecutorService executor = Executors.newFixedThreadPool(totalMiners);
        CompletionService<MiningResult> completionService = new ExecutorCompletionService<>(executor);
        List<Future<MiningResult>> futures = new ArrayList<>();

        // Add miners
        for (int i = 1; i <= totalMiners; i++) {
            final int minerId = i;
            futures.add(completionService.submit(() -> {
                MiningService miner = new MiningService(minerId);
                return miner.call();
            }));
        }

        // Winner Display
        MiningResult winner = completionService.take().get();
        System.out.println("\n## WINNER ## | Miner " + winner.getMinerId() + " with " + winner.getTotalGold() + " gold");

        // Cancel remaining miners
        for (Future<MiningResult> future : futures) {
            if (!future.isDone()) {
                future.cancel(true);
            }
        }

        // Shutdown process
        executor.shutdownNow();

    }

}