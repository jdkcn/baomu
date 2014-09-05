package com.jdkcn.baomu.listener;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.jdkcn.baomu.BaomuGuiceModule;

/**
 * 
 * @author rory
 */
@WebListener
public class WebAppListener extends GuiceServletContextListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new BaomuGuiceModule(), new ServletModule(){
            /**
             * {@inheritDoc}
             */
            @Override
            protected void configureServlets() {
            }
        });
    }

}
