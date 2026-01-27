package dk.project.service;

public class MiningResult {

    // Attributes
    private final int minerId;
    private final int totalGold;
    private final int strikes;
    private final long duration;

    // __________________________________________________________________

    public MiningResult(int minerId, int totalGold, int strikes, long duration) {
        this.minerId = minerId;
        this.totalGold = totalGold;
        this.strikes = strikes;
        this.duration = duration;
    }

    // __________________________________________________________________

    public int getStrikes() {
        return strikes;
    }

    // __________________________________________________________________

    public int getMinerId() {
        return minerId;
    }

    // __________________________________________________________________

    public int getTotalGold() {
        return totalGold;
    }

    // __________________________________________________________________

    public long getDuration() {
        return duration;
    }

    // __________________________________________________________________

    @Override
    public String toString() {
        return "Miner " + minerId + " finished with " + totalGold + " gold after " + strikes + " strikes (" + duration + " ms)";
    }

}