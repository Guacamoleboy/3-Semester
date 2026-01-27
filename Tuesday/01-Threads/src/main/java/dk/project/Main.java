package dk.project;

import dk.project.Service.CompressionService;
import dk.project.Service.CompressionServiceTwo;
import dk.project.Service.MicrowaveService;
import dk.project.Service.MicrowaveServiceTwo;
import javax.accessibility.AccessibleComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

    // Attributes
    private static final int maxThreads = 10;
    private static final String APP_LOG = """
        2024-01-15 10:23:45 INFO Application started
        2024-01-15 10:23:46 INFO Loading configuration
        2024-01-15 10:23:47 DEBUG Database connection established
        2024-01-15 10:23:48 INFO Server listening on port 8080
        2024-01-15 10:24:01 WARN High memory usage detected
        2024-01-15 10:24:15 ERROR Connection timeout to external service
        2024-01-15 10:24:16 INFO Retrying connection...
        2024-01-15 10:24:20 INFO Connection restored
        """;
    private static final String ACCESS_LOG = """
        192.168.1.100 - - [15/Jan/2024:10:23:45] "GET /api/users HTTP/1.1" 200 1234
        192.168.1.101 - - [15/Jan/2024:10:23:46] "POST /api/login HTTP/1.1" 200 567
        192.168.1.100 - - [15/Jan/2024:10:23:47] "GET /api/users/123 HTTP/1.1" 200 890
        192.168.1.102 - - [15/Jan/2024:10:23:48] "GET /api/products HTTP/1.1" 200 5678
        192.168.1.100 - - [15/Jan/2024:10:23:49] "DELETE /api/users/456 HTTP/1.1" 403 123
        """;
    private static final String ERROR_LOG = """
        ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR
        NullPointerException at com.app.Service.process(Service.java:42)
        ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR
        IllegalStateException at com.app.Handler.handle(Handler.java:88)
        ERROR ERROR ERROR ERROR ERROR ERROR ERROR ERROR
        """;


    // _______________________________________________

    public static void main(String[] args) throws Exception {

        /*
        // TASK 1

        // Initial
        List<MicrowaveService> programs = new ArrayList<>();
        programs.add(new MicrowaveService("Defrost", 2));
        programs.add(new MicrowaveService("Reheat", 4));
        programs.add(new MicrowaveService("Popcorn", 6));

        // Pool from fixed size of programs available
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(programs.size(), maxThreads));

        // Execute all programs
        for (MicrowaveService program : programs) {
            executor.execute(program);
        }

        // Shutdown
        executor.shutdown();
        */

        /*
        // TASK 2
        List<MicrowaveServiceTwo> programs = new ArrayList<>();
        programs.add(new MicrowaveServiceTwo("Defrost", 2));
        programs.add(new MicrowaveServiceTwo("Reheat", 4));
        programs.add(new MicrowaveServiceTwo("Popcorn", 6));

        // Thread max
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(programs.size(), maxThreads));

        // Future
        List<Future<Long>> futures = new ArrayList<>();

        // Future add / submit to executor
        for (MicrowaveServiceTwo program : programs) {
            Future<Long> future = executor.submit(program);
            futures.add(future);
        }

        // Display for-loop
        for (int i = 0; i < futures.size(); i++) {
            long duration = futures.get(i).get();
            System.out.println(programs.get(i).getProgram() + " took " + duration + " ms");
        }

        // Safe shutdown
        executor.shutdown();
        */

        /*
        // TASK 3
        List<Runnable> jobs = new ArrayList<>();
        jobs.add(new CompressionService("app.log", APP_LOG));
        jobs.add(new CompressionService("access.log", ACCESS_LOG));
        jobs.add( new CompressionService("error.log", ERROR_LOG));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(Runnable r : jobs){
            executor.execute(r);
        }

        executor.shutdown();
        */

        // TASK 4
        List<CompressionServiceTwo> jobs = new ArrayList<>();
        jobs.add(new CompressionServiceTwo("app.log", APP_LOG));
        jobs.add(new CompressionServiceTwo("access.log", ACCESS_LOG));
        jobs.add(new CompressionServiceTwo("error.log", ERROR_LOG));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Future<Double>> futures = new ArrayList<>();
        for (CompressionServiceTwo job : jobs) {
            futures.add(executor.submit(job));
        }

        double bestRatio = Double.MAX_VALUE;
        String bestFile = "";

        for (int i = 0; i < futures.size(); i++) {
            double ratio = futures.get(i).get();
            System.out.printf("%s ratio = %.3f%n", jobs.get(i).getFileName(), ratio);
            if (ratio < bestRatio) {
                bestRatio = ratio;
                bestFile = jobs.get(i).getFileName();
            }
        }

        System.out.printf("Best compression: %s (%.3f)%n", bestFile, bestRatio);
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

    }

}