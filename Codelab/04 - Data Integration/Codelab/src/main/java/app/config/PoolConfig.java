package app.config;

import java.net.http.HttpClient;
import java.util.concurrent.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoolConfig {

    // POOL SIZE -> CPU Cores + 1 -> Number
    // 4 for HttpRequests is fine for now though. Logginw till log if pressured.

    // Attributes
    private static final Logger logger = LoggerFactory.getLogger(PoolConfig.class);
    private static final int POOL_SIZE = 4;
    private static final PoolLog EXECUTOR = new PoolLog(POOL_SIZE, POOL_SIZE, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    private static final HttpClient CLIENT =
    HttpClient.newBuilder()
    .executor(EXECUTOR)
    .version(HttpClient.Version.HTTP_1_1)
    .build();

    // _______________________________________________________
    // Non-instantiatable for safety

    private PoolConfig() {
    }

    // _____________________________________________________________________

    public static ExecutorService getExecutor() {
        return EXECUTOR;
    }

    // _____________________________________________________________________

    public static HttpClient getClient() {
        return CLIENT;
    }

    // _____________________________________________________________________

    public static void shutdown() {
        logger.info("\nShutting down thread pool");
        EXECUTOR.shutdown();
    }

}