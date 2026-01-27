package dk.project.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class MicrowaveServiceTwo implements Callable<Long> {

    // Attributes
    private String program;
    private int duration;

    // _______________________________________________________

    public MicrowaveServiceTwo(String program, int duration) {
        this.program = program;
        this.duration = duration;
    }

    // _______________________________________________________

    @Override
    public Long call() throws Exception {

        // Setup
        genericMessage("Started");
        long startTime = genericTime();
        tickPerSecond();
        long endTime = genericTime();
        genericMessage("Ended");

        // Return
        return endTime - startTime;

    }

    // _______________________________________________________

    private void genericMessage(String msg) {
        System.out.println(program + " | " + msg);
    }

    // _______________________________________________________

    private long genericTime() {
        return System.currentTimeMillis();
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    // _____________________________________________________

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}