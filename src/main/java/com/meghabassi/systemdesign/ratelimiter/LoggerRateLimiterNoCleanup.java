package com.meghabassi.systemdesign.ratelimiter;

import java.util.HashMap;

/**
 * Solution to problem
 * https://leetcode.com/problems/logger-rate-limiter
 */
public class LoggerRateLimiterNoCleanup extends LoggerRateLimiter {

    private static final int EXPIRATION_TIME = 10;

    public LoggerRateLimiterNoCleanup() {
        super(EXPIRATION_TIME);
    }

    public Boolean shouldPrintMessage(int timestamp, String message) {
        if(message ==null)
            return null;
        int oldTimeStamp=this.messageTimeStampMap.getOrDefault(message,-1);
        if(oldTimeStamp != -1 && timestamp-oldTimeStamp <EXPIRATION_TIME )
            return false;
        this.messageTimeStampMap.put(message,timestamp);
        return true;
    }
}