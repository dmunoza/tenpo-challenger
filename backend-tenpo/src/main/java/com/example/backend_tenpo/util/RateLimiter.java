package com.example.backend_tenpo.util;

import com.example.backend_tenpo.excepton.TooManyRequestException;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final int maxRequestPerMinute;
    private final long timeWindowsMillis;
    private final Map<String, RequestInfo> clients = new ConcurrentHashMap<>();

    public RateLimiter(int maxRequestPerMinute, long timeWindowsMillis) {
        this.maxRequestPerMinute = maxRequestPerMinute;
        this.timeWindowsMillis = timeWindowsMillis;
    }

    public synchronized boolean isAllowed(String clientId) {
        RequestInfo requestInfo = clients.get(clientId);
        long now = Instant.now().toEpochMilli();

        if (requestInfo == null) {
            requestInfo = new RequestInfo(1, now);
            clients.put(clientId, requestInfo);
            return true;
        }

        if (now - requestInfo.startTime > timeWindowsMillis) {
            requestInfo.startTime = now;
            requestInfo.requestCount = 1;
            return true;
        }

        if (requestInfo.requestCount < maxRequestPerMinute) {
            requestInfo.requestCount++;
            return true;
        }

        throw new TooManyRequestException("Too many requests: " + clientId);
    }

    private static class RequestInfo {
        int requestCount;
        long startTime;

        RequestInfo(int requestCount, long startTime) {
            this.requestCount = requestCount;
            this.startTime = startTime;
        }
    }

}
