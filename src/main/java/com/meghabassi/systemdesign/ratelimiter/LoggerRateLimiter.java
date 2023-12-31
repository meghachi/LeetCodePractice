package com.meghabassi.systemdesign.ratelimiter;

import java.util.HashMap;

public abstract class LoggerRateLimiter {
    private int expirationTime = 10;
    protected HashMap<String, Integer> messageTimeStampMap;
    public abstract Boolean shouldPrintMessage(int timestamp, String message) ;
    LoggerRateLimiter(int expirationTime){
        this.messageTimeStampMap=new HashMap<>();
        this.expirationTime=expirationTime;
    }
    public int getExpirationTime(){
        return this.expirationTime;
    }
}
