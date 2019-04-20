package com.commerce.huayi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Deque;
import java.util.concurrent.*;

@Service("threadService")
public class ThreadService {
    private static Logger log = LoggerFactory.getLogger(ThreadService.class);
    private ThreadPoolExecutor threadPool;
    private final Deque<Runnable> taskQueue = new LinkedBlockingDeque<Runnable>();

    public ThreadService() {
        this.start();
    }

    public void start() {
        this.threadPool = new ThreadPoolExecutor(5, 10, 60000L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
            public Thread newThread(Runnable r) {
                return new Thread(r, super.getClass().getName());
            }
        });
        this.threadPool.allowCoreThreadTimeOut(true);
    }

    @PreDestroy
    public void stop() {
        try {
            this.threadPool.shutdown();
        } catch (Exception e) {
            log.error("关闭线程池出现异常", e);
        }
    }

    public void submit(Runnable task) {
        this.threadPool.execute(task);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return this.threadPool.submit(task);
    }

    public int getActiveCount() {
        return this.threadPool.getActiveCount();
    }

    public int getPoolSize() {
        return this.threadPool.getPoolSize();
    }

    public int getCacheSize() {
        return this.taskQueue.size();
    }

    public String getStatus() {
        return "poolSize=" + getPoolSize() + "ActiveCount=" + getActiveCount() + " queueSize=" + this.threadPool.getQueue().size() + " cacheSize=" + getCacheSize();
    }
}