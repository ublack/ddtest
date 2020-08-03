package com.ublack.ddtest;

import java.util.LinkedList;

public class CircuitBreaker {

    private Object defObj = new Object();
    private int totalThreshold;
    private int failThreshold;
    private int failCount = 0;
    private LinkedList<Boolean> totalData;
    private long delayTime = 60000; // 熔断时, 延迟的毫秒数
    private long lastBreakTime = System.currentTimeMillis() - delayTime;


    public CircuitBreaker(int totalThreshold, int failThreshold) {
        this.totalThreshold = totalThreshold;
        this.failThreshold = failThreshold;
        totalData = new LinkedList<Boolean>();
    }


    public int getTotalThreshold() {
        return totalThreshold;
    }

    public void setTotalThreshold(int totalThreshold) {
        this.totalThreshold = totalThreshold;
    }

    public int getFailThreshold() {
        return failThreshold;
    }

    public void setFailThreshold(int failThreshold) {
        this.failThreshold = failThreshold;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    Boolean runAllowed() {
        if ((System.currentTimeMillis() - lastBreakTime) <= delayTime) {
            return false;
        }
        if (totalData.size() < totalThreshold) {
            return true;
        }
        if (failCount > failThreshold) {
            reset();
            return false;
        }
        return true;
    }

    Object fallback(){
        return defObj;
         // do something or return some default object ;
    }

    void fail(){
        if (totalData.size() == totalThreshold) {
            Boolean first = totalData.removeFirst();
            if (first == Boolean.FALSE) {
                failCount--;
            }
        }
        failCount++;
        totalData.addLast(Boolean.FALSE);
    }
    void success(){
        if (totalData.size() == totalThreshold) {
            Boolean first = totalData.removeFirst();
            if (first == Boolean.FALSE) {
                failCount--;
            }
        }
        totalData.addLast(Boolean.TRUE);
    }


    void reset() {
        failCount = 0 ;
        totalData.clear();
        lastBreakTime = System.currentTimeMillis();
    }

}
