package dk.project.Service;

import java.util.concurrent.TimeUnit;

public class MicrowaveService implements Runnable {

    // Attributes
    private String program; // Program Selection
    private int duration; // Total Duration

    // _____________________________________________________

    public MicrowaveService(String program, int duration){
        this.program = program;
        this.duration = duration;
    }

    // _____________________________________________________

    @Override
    public void run() {
        try {
            startMessage();
            tickPerSecond();
            finishMessage();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(program + " | interrupted");
        }
    }

    // _____________________________________________________

    private void startMessage() {
        System.out.println(program + " | started");
    }

    // _____________________________________________________

    private void tickPerSecond() throws InterruptedException {
        int tick = 0;

        while (tick < duration) {
            TimeUnit.SECONDS.sleep(1);
            tick++;
            System.out.println(program + " | tick " + tick);
        }

    }

    // _____________________________________________________

    private void finishMessage() {
        System.out.println(program + " | finished");
    }

}