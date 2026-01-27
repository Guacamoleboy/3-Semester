package dk.project.Service;

import java.util.HashSet;
import java.util.Set;

public class CompressionService implements Runnable {

    // Attributes
    private String name;
    private String content;

    // ___________________________________________________

    public CompressionService(String name, String content){
        this.name = name;
        this.content = content;
    }

    // ___________________________________________________

    @Override
    public void run(){
        startMessage();
        int uniqueCount = compress();
        finishMessage(uniqueCount);
    }

    // ___________________________________________________

    private void startMessage() {
        System.out.println("Compressing " + name + "...");
    }

    // ___________________________________________________

    private int compress() {
        Set<Character> uniqueChars = new HashSet<>();
        for (char c : content.toCharArray()) {
            uniqueChars.add(c);
        }
        return uniqueChars.size();
    }

    // ___________________________________________________

    private void finishMessage(int uniqueCount) {
        System.out.println("Done " + name + " | " + uniqueCount + " unique chars");
    }

}