package com.meghabassi.systemdesign.ratelimiter;

import java.util.HashMap;

public class LoggerRateLimiterTwoMap extends LoggerRateLimiter {
    private static  final int EXPIRATION_TIME = 10;
    private HashMap<String,Integer> oldMap;
    private static int lastTimeStamp=Integer.MIN_VALUE;
    private static final int HASH_EXPIRATION_TIME= EXPIRATION_TIME *2;
    public LoggerRateLimiterTwoMap() {

        super(EXPIRATION_TIME);
        oldMap=new HashMap<>();

    }
    public Boolean shouldPrintMessage(int timestamp, String message) {
        if(message ==null)
            return null;

        if(timestamp -lastTimeStamp >=HASH_EXPIRATION_TIME) {
            HashMap<String,Integer> tempMap=this.oldMap;
            this.oldMap= messageTimeStampMap;
            this.messageTimeStampMap=tempMap;
            this.messageTimeStampMap.clear();
        }
        int oldTimeStamp=this.messageTimeStampMap.getOrDefault(message,-1);
        if(oldTimeStamp != -1 && timestamp-oldTimeStamp <this.getExpirationTime() )
            return false;
 
        messageTimeStampMap.put(message,timestamp);
        return true;
    }
}
