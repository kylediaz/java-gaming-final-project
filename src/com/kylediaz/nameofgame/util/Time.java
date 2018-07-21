package com.kylediaz.nameofgame.util;

import java.util.ArrayList;

/**
 * keeps track of nanoseconds since last refresh
 * @author kyled
 */
public final class Time {
    
    private Time() {
        
    }
    
    public final static void refresh() {
        deltaTime = (System.currentTimeMillis() - lastUpdate);
        lastUpdate = System.currentTimeMillis();
        surpassedTime = (int) (System.currentTimeMillis() - startTime);
    }
    private static long startTime = System.currentTimeMillis();
    private static int surpassedTime = 0;
    public final static int surpassedTimeMilli() {
        return surpassedTime;
    }
    private static long lastUpdate = System.currentTimeMillis();
    private static double deltaTime = 0;
    
    /**
     * @return time since last refresh in milliseconds
     */
    public final static double deltaTimeMilli() {
        return deltaTime;
    }
    public final static void reset() {
        startTime = System.currentTimeMillis();
    }
}
