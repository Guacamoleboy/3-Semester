package app.config;

import io.github.cdimascio.dotenv.Dotenv;

// Created by: Guacamoleboy
// ________________________
// Last updated: 24/02-2026
// By: Guacamoleboy

public class DotEnv {

    // Attributes
    private static final Dotenv dotenv;
    private static final String environment = "development";
    private static final String fileName;

    // _________________________________________________
    // Usage:
    // ______
    // DotEnv.getTmdbKey(). Returns a String.

    static {

        // Environment setup + file definition
        // set.env is used for terminal access (change of .env file).
        // For example running the program in terminal with test as environement will
        // load .env.test. If there's no test available it'll fall back to our environement attribute.

        String environmentLoad = System.getProperty("set.env", environment);
        fileName = ".env." + environmentLoad;

        // Load (I/O) the .env.development file
        dotenv = Dotenv.configure()
                .directory("src/main/resources")
                .filename(fileName)
                .ignoreIfMissing()
                .load();

    }

    // _________________________________________________

    public static String get(String key) {
        String row = dotenv.get(key);
        if (row == null){
            System.out.println("No value found in: " + fileName + ".");
        }
        return row;
    }

}