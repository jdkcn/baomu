package com.jdkcn.baomu;

import com.google.inject.AbstractModule;
import com.jdkcn.baomu.checker.Checker;
import com.jdkcn.baomu.checker.DefaultChecker;

/**
 * The main guice module.
 * 
 * @author rory
 */
public class BaomuGuiceModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bind(Checker.class).to(DefaultChecker.class);
    }

}
