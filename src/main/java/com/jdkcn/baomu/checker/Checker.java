package com.jdkcn.baomu.checker;

/**
 * The health checker.
 * 
 * @author rory
 */
public interface Checker {

    /**
     * check this url health or not.
     * 
     * @param url
     *            the url to check.
     * @return true for health, false for not health.
     */
    boolean health(String url);
}
