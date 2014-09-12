package com.jdkcn.baomu.filter;

import javax.servlet.annotation.WebFilter;

import com.google.inject.servlet.GuiceFilter;

/**
 * The GuiceFilter.
 * 
 * @author rory
 */
@WebFilter(filterName = "Guice Filter", urlPatterns = {"/*"})
public class BaomuGuiceFilter extends GuiceFilter{

}
