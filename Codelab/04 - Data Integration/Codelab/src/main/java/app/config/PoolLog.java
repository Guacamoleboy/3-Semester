package app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

public class PoolLog extends ThreadPoolExecutor {

    // Attributes
    private static final Logger logger = LoggerFactory.getLogger(PoolLog.class);

    // __________________________________________________________________

    public PoolLog(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    // __________________________________________________________________
    // Using slf4j {} logging just like String.format

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);

        if (t != null) {
            logger.error("Task execution failed", t);
        }

        // Debug
        // logger.info("Task finished on thread {}", Thread.currentThread().getName());

        int active = getActiveCount();
        int max = getMaximumPoolSize();
        int queued = getQueue().size();

        // Debug
        // logger.debug("ThreadPool OK | Active: {}/{} | Queue: {}",
        // active, max, queued);

        if (active >= max || queued > 0) {
            logger.warn("ThreadPool pressure detected | Active: {}/{} | Queue: {}",
                active, max, queued);
        }

    }

}