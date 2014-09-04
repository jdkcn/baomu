package com.jdkcn.baomu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jdkcn.baomu.checker.Checker;

/**
 * The bootstrap for the baomu.
 * 
 * @author Rory
 */
public class Baomu {

    private static final Logger LOGGER = LoggerFactory.getLogger(Baomu.class);

    /**
     * The main method.
     * 
     * @param args
     *            Arguments for bootstrap.
     */
    public static void main(String... args) {
        Injector injector = Guice.createInjector(new BaomuGuiceModule());
        Checker checker = injector.getInstance(Checker.class);
        LOGGER.info("http://jdkcn.com health:{}", checker.health("http://jdkcn.com"));
    }
}
