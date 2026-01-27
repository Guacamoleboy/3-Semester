package dk.project.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class CompressionServiceTwo implements Callable<Double>{

    // Attributes
    private String name;
    private String content;

    // ____________________________________________________________

    public CompressionServiceTwo(String name, String content){
        this.name = name;
        this.content = content;
    }

    // ____________________________________________________________

    @Override
    public Double call() throws Exception {
        startMessage();
        int uniqueCount = compress();
        finishMessage(uniqueCount);
        return ratio(uniqueCount);
    }

    // ____________________________________________________________

    private void startMessage() {
        System.out.println("Compressing " + name + "...");
    }

    // ____________________________________________________________

    private void finishMessage(int uniqueCount) {
        System.out.println("Done " + name + " | " + uniqueCount + " unique chars");
    }

    // ____________________________________________________________

    private int compress() {
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : content.toCharArray()) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(name + " interrupted!");
                return 0;
            }
            uniqueChars.add(c);
        }
        return uniqueChars.size();
    }

    // ____________________________________________________________

    private double ratio(int uniqueCount) {
        return (double) uniqueCount / content.length();
    }

    // ____________________________________________________________

    public String getFileName() {
        return name;
    }

}