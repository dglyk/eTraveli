package com.example.etraveli.service;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
public class TaskExecutionServiceImpl implements TaskExecutionService{

    static final Logger logger = LoggerFactory.getLogger(TaskExecutionServiceImpl.class);
    private BlockingQueue<HttpServletRequest> blockingQueue;

    public TaskExecutionServiceImpl() {
        blockingQueue = new LinkedBlockingDeque<>();
        initiateThread();
    }

    /**
     * Single Thread on Which Tasks will be performed
     */
    private void initiateThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (!blockingQueue.isEmpty()) {
                        logger.info("Processing Next Task from Queue");
                        HttpServletRequest taskDetail = blockingQueue.take();
                        boolean result = true; // processTask(taskDetail);
                        if (result)
                            logger.info("Task performed successfully");
                        else
                            logger.error("Task compelted with errors");
                        // Now we can either email or publish push notification
                        // for task completion to respective user.
                    }
                } catch (InterruptedException e) {
                    logger.error("There was an error while processing ", e);
                    Thread.currentThread().interrupt();
                    initiateThread();
                }
            }
        });
        thread.setName("MyCustomThread");
        thread.start();
        logger.info("Worker Thread {} initiated successfully", thread.getName());
    }
}
