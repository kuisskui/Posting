package com.example.Posting.ratelimit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
public class RateLimitingAspect {
    private static final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private static final int REQUEST_LIMIT = 100;
    private static final long TIME_LIMIT = 60000; // 1 minute

    @Before("@annotation(RateLimited)")
    public void beforeRequest(JoinPoint joinPoint) {
        String clientId = getClientId(joinPoint); // Implement method to get client ID
        AtomicInteger count = requestCounts.computeIfAbsent(clientId, k -> new AtomicInteger(0));
        if (count.incrementAndGet() > REQUEST_LIMIT) {
            throw new RateLimitExceededException("Rate limit exceeded");
        }
        if (requestCounts.size() == 1) {
            resetRequestCounts();
        }
    }

    private String getClientId(JoinPoint joinPoint) {
        // Implement logic to extract client ID from the joinPoint or any other source
        // For example, if client ID is passed as an argument to the method, you can extract it from joinPoint.getArgs()
        return "someClientId";
    }

    private void resetRequestCounts() {
        new Thread(() -> {
            try {
                Thread.sleep(TIME_LIMIT);
                requestCounts.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
